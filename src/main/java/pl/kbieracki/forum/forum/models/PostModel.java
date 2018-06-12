package pl.kbieracki.forum.forum.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kbieracki.forum.forum.models.forms.PostForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "post_forum")
@Entity
@Data
@NoArgsConstructor
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String title;
    @NotNull
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "post")
    List<CommentModel> comments;

    private int rating;

    public PostModel(PostForm postform){
        this.title = postform.getTitle();
        this.text = postform.getText();
    }
}
