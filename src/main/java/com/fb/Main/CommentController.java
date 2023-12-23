package com.fb.Main;

import com.fb.components.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CommentController {
    @FXML
    private Label CommentLabel;

    public void setCommentData(Comment comment){
        CommentLabel.setText(comment.getText());
    }
}