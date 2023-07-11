package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
    public Entry create(Entry entry) {
       return entryService.createEntry(entry);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an entry.", description = "Deletes an entry by ID.")
    public void deleteEntry(@PathParam("id") Long id) {
        Entry entry = entryService.findById(id);
        if (entry != null) {
            entryService.deleteEntry(entry);
        } else {
            throw new NotFoundException("Entry not found");
        }
    }
    
    @PUT
    @Path("/{id}")
    @Operation(summary = "Edits an entry.", description = "Edits an entry by ID.")
    public Entry editEntry(@PathParam("id") Long id, Entry entry) {
        Entry updatedEntry = entryService.findById(id);
        if (updatedEntry != null) {
            return entryService.editEntry(id, updatedEntry, entry);
        } else {
            throw new NotFoundException("Entry not found");
        }
    }
}
