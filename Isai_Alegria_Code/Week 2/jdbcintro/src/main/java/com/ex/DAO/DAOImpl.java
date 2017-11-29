package com.ex.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ex.pjos.Artist;
import com.ex.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class DAOImpl implements DAO{

	public ArrayList<Artist> getArtists() {
		
		ArrayList<Artist> artists = new ArrayList<Artist>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "select * from artist";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				
				Artist temp = new Artist();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				artists.add(temp);
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artists;
	}

	@Override
	public Artist getArtistsById(int id) {
		Artist art = new Artist();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "select * from artist where artistid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet info = ps.executeQuery();
			
			while(info.next()) {
				
				art.setId(info.getInt(1));
				art.setName(info.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return art;
	}

	@Override
	public Artist addArtist(String name) {

		Artist art = new Artist();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			conn.setAutoCommit(false);
			String sql = "insert into artist (Name) values (?)";
			String[] key = new String[1];
			key[0] = "artistid";
			
			PreparedStatement ps = conn.prepareStatement(sql,key);
			ps.setString(1, name);
			int rows = ps.executeUpdate();
			//int id = 0;
			if(rows != 0) {
			
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					art.setId(pk.getInt(1));
				}
				art.setName(name);
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return art;
	}

	@Override
	public Artist updateById(int id,String name) {
		
		Artist art = new Artist();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			conn.setAutoCommit(false);
			String sql = "update artist set name=(?) where artistid =(?)";
			int ret = 0;
			//String[] key = new String[2];
			//key[0] = "artistid";
			//key[1] = "name";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(2,name);
			ps.setInt(1, id);
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return getArtistsById(id);
	}

	public Artist getNameById(int id) {
		
		Artist artist = new Artist();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){

			String sql = "{? = get_artist_by_id(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR); // for the variable holing return value
			cs.setInt(2, id); // for the id we are going to search the database for
			cs.execute();
			
			artist.setName(cs.getString(1));
			artist.setId(cs.getInt(2));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return artist;
		
	}
	
	public List<Artist> getArtistsStoredProc() {
		
		List<Artist> artists = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "{call get_all_artists(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			
			int numRows = cs.executeUpdate();
			System.out.println("number of row affected " + numRows);
			ResultSet rs = (ResultSet) cs.getObject(1);
			while(rs.next()) {
				artists.add(new Artist(rs.getInt(1),rs.getString(2)));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artists;
	}
	
	
}
