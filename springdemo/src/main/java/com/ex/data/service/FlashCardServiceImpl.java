package com.ex.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.data.dao.FlashCardRepository;
import com.ex.data.domain.FlashCard;

@Service
@Transactional
public class FlashCardServiceImpl implements FlashCardService
{

	@Autowired
	private FlashCardRepository fcRepo;
	@Override
	public void addFlashCard(FlashCard fc) {
		// TODO Auto-generated method stub
		fcRepo.save(fc);
	}

	@Override
	public List<FlashCard> findAllFlashCards() {
		// TODO Auto-generated method stub
		return fcRepo.findAll();
	}

	@Override
	public FlashCard findFlashCardByQuestion(String question) {
		// TODO Auto-generated method stub
		return fcRepo.findFlashCardByQuestion(question);
	}
	
}
