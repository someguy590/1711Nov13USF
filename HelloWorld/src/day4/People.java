package day4;

public class People 
{
	
	String name;
	int age;
	String place;
	public People(String name, int age,String place) 
	{
		super();
		this.name = name;
		this.age = age;
		this.place = place;
		
	}
	public People() 
	{
	
		
	}
	/**
	 * @return the name
	 */
	public String toString() 
	{
		return name + ":" + age+":"+place+"\n";
	}
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
}