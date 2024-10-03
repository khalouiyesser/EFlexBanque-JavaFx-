package controllers.commentaireArticle;

import Entities.actualites.CommentaireHadhemi;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.ServiceCommentaireHadhemi;
import javafx.fxml.FXML;


public class CommentaireItemFrontController {


    @FXML
    private Text commentAff;

    @FXML
    private HBox commenterHbox;

    @FXML
    private Text nomCom;



    public void initData(CommentaireHadhemi commentaireHadhemi) {
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        commentAff.setText(commentaireHadhemi.getContenu());
        nomCom.setText(commentaireHadhemi.getNom_aut_com());
        System.out.println("contenue du commmmmmm"+commentAff);



    }




}
