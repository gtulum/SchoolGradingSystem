package com.school.SchoolGradingSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.SchoolGradingSystem.model.Mark;
import com.school.SchoolGradingSystem.model.MarkRepository;

@Service
public class MarkService {
	
	@Autowired
	private MarkRepository markRepository;
	
	public List<Mark> getAllMarks()
    {
        List<Mark> markList = (List<Mark>) markRepository.findAll();
         
        if(markList.size() > 0) {
            return markList;
        } else {
            return new ArrayList<Mark>();
        }
    }
	
	public Mark getMark(long id) {
		return markRepository.findById(id);
	}
	
	public void addMark(Mark mark) {
		markRepository.save(mark);
	}
	
	public void updateMark(long id,Mark mark) {
		
		Mark mrk = markRepository.findById(id);	
		mrk.setMark(mark.getMark());
		mrk.setMarkDate(mark.getMarkDate());
		markRepository.save(mrk);		         
	}
	
	public void deleteMark(long id) {
		markRepository.deleteById(id);
	}


}
