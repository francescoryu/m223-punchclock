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

import ch.zli.m223.service.TagService;

@Path("/tags")
@Tag(name = "Tags", description = "Handling of tags")
public class TagController {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all tags.")
    public List<ch.zli.m223.model.Tag> index() {
        return tagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
    public ch.zli.m223.model.Tag create(ch.zli.m223.model.Tag tag) {
       return tagService.createTag(tag);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an tag.", description = "Deletes an tag by ID.")
    public void deleteTag(@PathParam("id") Long id) {
        ch.zli.m223.model.Tag tag = tagService.findById(id);
        if (tag != null) {
            tagService.deleteTag(tag);
        } else {
            throw new NotFoundException("Tag not found");
        }
    }
    
    @PUT
    @Path("/{id}")
    @Operation(summary = "Edits an tag.", description = "Edits an tag by ID.")
    public ch.zli.m223.model.Tag editTag(@PathParam("id") Long id, ch.zli.m223.model.Tag tag) {
        ch.zli.m223.model.Tag updatedTag = tagService.findById(id);
        if (updatedTag != null) {
            return tagService.editTag(id, updatedTag, tag);
        } else {
            throw new NotFoundException("Tag not found");
        }
    }
}
