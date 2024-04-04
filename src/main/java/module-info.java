module dev.thekarancode.vcardel {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.thekarancode.vCardelApp to javafx.fxml;
    exports dev.thekarancode.vCardelApp;
}