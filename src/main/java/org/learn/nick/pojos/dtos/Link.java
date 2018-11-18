package org.learn.nick.pojos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Link {
    private final String id;
    private final String url;
    private final String description;

    public Link(String url, String description) {
        this(null, url, description);
    }

    public Link(Link link) {
        this(link.getId(), link.getUrl(), link.getDescription());
    }
}
