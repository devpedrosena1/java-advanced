package br.com.fiap.tds.javaadv.Library.domainmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "POSTS")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "TITLE", length = 100, nullable = false)
    private @Getter @Setter String title;

    @Column(name = "CONTENT", length = 255, nullable = false)
    private @Getter @Setter String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private @Getter @Setter User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "POSTS_TAGS",
            joinColumns = @JoinColumn(name = "POST_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private @Getter @Setter Set<Tag> tags;

}
