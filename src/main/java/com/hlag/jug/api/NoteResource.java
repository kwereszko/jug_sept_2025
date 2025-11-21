package com.hlag.jug.api;

import com.hlag.jug.model.Note;
import com.hlag.jug.service.NoteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class NoteResource {

	private final NoteService noteService;

	@GET
	public List<Note> list() {
		log.info("\n\nList notes\n\n");
		return Note.listAll();
	}

	@POST
	@Transactional
	public Note add(Note note) {
		note.persist();
		return note;
	}

	@GET
	@Path("/{id}")
	public Note get(@PathParam("id") Long id) {
		return noteService.findNote(id);
	}

	@GET
	@Path("/merge")
	public Note merge(@QueryParam("id1") Long id1, @QueryParam("id2") Long id2) {
		return noteService.merge(id1, id2);
	}

}
