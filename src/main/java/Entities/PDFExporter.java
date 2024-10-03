package Entities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFExporter {

    public PDFExporter() {
    }

    public void generatePDF(GridPane gridPane) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                // Title
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.RED); // Red color and bigger size
                Paragraph title = new Paragraph("Liste des evenements", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20); // Add spacing after title
                document.add(title);

                PdfPTable pdfTable = new PdfPTable(gridPane.getColumnConstraints().size());
                addTableHeaders(pdfTable, gridPane);
                addTableData(pdfTable, gridPane);
                document.add(pdfTable);
                document.close();
                System.out.println("PDF generated successfully.");
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void addTableHeaders(PdfPTable pdfTable, GridPane gridPane) {
        int numColumns = gridPane.getColumnConstraints().size();
        for (int i = 0; i < numColumns; i++) {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(new BaseColor(0, 121, 107)); // Green color
            header.setBorderWidth(1);
            Text headerText = (Text) gridPane.getChildren().get(i);
            header.setPhrase(new Phrase(headerText.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
            pdfTable.addCell(header);
        }
    }
    private void addTableData(PdfPTable pdfTable, GridPane gridPane) {
        ObservableList<Node> children = gridPane.getChildren();
        int numRows = gridPane.getRowConstraints().size();
        int numColumns = gridPane.getColumnConstraints().size();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Node node = getNodeByRowColumnIndex(i, j, gridPane);
                String cellValue = (node instanceof Label) ? ((Label) node).getText() : ""; // Assuming nodes are Labels
                PdfPCell cell = new PdfPCell(new Phrase(cellValue));
                cell.setBorderWidth(1);
                pdfTable.addCell(cell);
            }
        }
    }
     private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}
