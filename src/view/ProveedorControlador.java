package view;

import java.io.IOException;

import control.Conexion;
import control.ProveedorControl;
import entity.Proveedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProveedorControlador {


	@FXML
	private Pane panel;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtTelefono;

	public Proveedor proveedor;


	public void initialize() {

	}

	public void ingresar (ActionEvent event) {		
		Pane pane;
		//try {
		newProveedor();
		System.out.println("Proveedor registrado");
		/*pane = (AnchorPane)FXMLLoader.load(getClass().getResource("frmPedidos.fxml"));
			Scene nuevaEscena = new Scene(pane);
			Stage ventana = (Stage) panel.getScene().getWindow();
			ventana.setScene(nuevaEscena);*/
		//} catch (IOException e) {
		//e.printStackTrace();
		//}
	}

	public void newProveedor () {

		String nombre = txtNombre.getText().toString();
		int telefono = Integer.parseInt(txtTelefono.getText().toString());	

		proveedor = new Proveedor(nombre,telefono);
		ProveedorControl  proveedorControl = new ProveedorControl(new Conexion());
		try {
			proveedorControl.insert(proveedor);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atras (ActionEvent event) {

		Pane pane;
		try {
			pane = (AnchorPane)FXMLLoader.load(getClass().getResource("frmPedidos.fxml"));
			Scene nuevaEscena = new Scene(pane);
			Stage ventana = (Stage) panel.getScene().getWindow();
			ventana.setScene(nuevaEscena);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
