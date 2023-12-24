package com.fb.Main;

import com.fb.components.Comment;
import com.fb.components.Post;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PostController implements Initializable{
    @FXML
    private Label UsernameLabel;
    @FXML
    private Label PostCaption;
    @FXML
    private Label likes;
    @FXML
    private HBox comment;
    @FXML
    private HBox commentContainer;
    @FXML
    private Button commentExitButton;
    @FXML
    private VBox   commentTextContainer ;
    @FXML
    private HBox commentEnterTextContainer;
    @FXML
    private VBox ReplyContainer;
    @FXML
    private Button ReplyButton;
    @FXML
    public TextField C_RTextField;
    private long startTime =0;
    public void  onCommentPressed(MouseEvent event){
        startTime = System.currentTimeMillis();
    }

    public void onCommentReleased(MouseEvent event) {
        if(System.currentTimeMillis()-startTime>5)
        {
            commentContainer.setVisible(true);
            commentEnterTextContainer.setVisible(true);
        }
    }
    public void commentExit(ActionEvent event)
    {
        commentContainer.setVisible(false);
        commentEnterTextContainer.setVisible(false);
    }
    public void AddComment (){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CommentText.fxml"));
            Parent CommentNode = fxmlLoader.load();
            CommentController commentController = fxmlLoader.getController();
            Post post1 = null;
            if(C_RTextField!=null) {
                for(Post p : UserManager.users.get(0).getPosts())
                {
                    if(p.getContent().equals(PostCaption.getText())){
                        post1 = p ;
                    }
                }
                System.out.println(post1.getContent());
                Comment comment = new Comment(UserManager.users.get(0).getId(), post1.getId(),C_RTextField.getText());
                post1.addComment(comment);
                UserManager.users.get(0).createPost(post1.getId(), post1.getContent(), post1.getPrivacy());
                commentController.setCommentData(comment);
            }
            commentTextContainer.getChildren().add(CommentNode);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLike(ActionEvent event){
        for(Post post:UserManager.users.get(0).getPosts()){
            if(post.getContent().equals(PostCaption.getText())){
                post.addLiker(UserManager.users.get(0).getId());
                break;
            }
        }
    }
    public void setPostData (Post post){
        User user = UserManager.getUserByUserId(post.getUserId());
        UsernameLabel.setText(user.getName());
        PostCaption.setText(post.getContent());
        likes.setText(String.valueOf(post.getNumberOfLikes()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Post p : UserManager.users.get(0).getPosts())
            {
                for (Comment c : p.getComments())
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CommentText.fxml"));
                    Parent CommentNode = fxmlLoader.load();
                    CommentController commentController = fxmlLoader.getController();
                    commentController.setCommentData(c);
                    commentTextContainer.getChildren().add(CommentNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}