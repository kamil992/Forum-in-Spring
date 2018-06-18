package pl.kbieracki.forum.forum.models.forms;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostForm {
    private int id;
    private String title;
    private String text;
    private String category;
}
