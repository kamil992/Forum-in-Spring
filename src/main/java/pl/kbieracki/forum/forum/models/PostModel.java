package pl.kbieracki.forum.forum.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kbieracki.forum.forum.models.forms.PostForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public PostModel(PostForm postform){
        this.title = postform.getTitle();
        this.text = postform.getText();
    }
}
