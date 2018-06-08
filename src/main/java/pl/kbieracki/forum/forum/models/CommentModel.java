package pl.kbieracki.forum.forum.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_forum")
@NoArgsConstructor
@Data
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    @NotNull
    private String comment;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

}
