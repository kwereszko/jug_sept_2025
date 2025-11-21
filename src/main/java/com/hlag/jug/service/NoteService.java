package com.hlag.jug.service;

import com.hlag.jug.model.Note;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class NoteService {

	public Note merge(Note note1, Note note2) {
		return new Note(note1.getTitle() + " & " + note2.getTitle(),
				note1.getContent() + "\n" + note2.getContent(),
				note1.getType().ordinal() > note2.getType().ordinal() ? note1.getType() : note2.getType());
	}

	// an example to show a difference between Quarkus usage of Jakarta CDI and Spring AOP
	public void example() {
		internalMethod(); // this call will not be intercepted by any AOP proxies
	}

	@Transactional
	private void internalMethod() {
		log.info("Internal, transactional method called");
	}

}
