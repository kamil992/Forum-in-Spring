package pl.kbieracki.forum.forum.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "post_rating")
@Entity
@NoArgsConstructor
@Data
public class PostRatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_id")
    private int postId;

    public PostRatingModel(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
