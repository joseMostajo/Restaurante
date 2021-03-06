package control;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.DetalleVenta;

public class DetalleVentaControl implements Control<DetalleVenta> {

	private Conexion conexion;

	public DetalleVentaControl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<DetalleVenta> list() throws Throwable {
		ArrayList<DetalleVenta> listaDetalleVenta = new ArrayList<DetalleVenta>();
		ResultSet rs;

		conexion.SQL("SELECT * FROM DETALLEVENTA");
		rs = conexion.resultSet();

		while (rs.next()) {
			int idDetalleVenta = rs.getInt("idDetalleVenta");
			int idVenta = rs.getInt("idVenta");
			int idPlato = rs.getInt("idPlato");
			int cantidad = rs.getInt("cantidad");

			listaDetalleVenta.add(new DetalleVenta(idDetalleVenta, idVenta, idPlato, cantidad));
		}

		return listaDetalleVenta;
	}

	public void insert(DetalleVenta detalleventa) throws Throwable {
		conexion.SQL("INSERT INTO DETALLEVENTA(idVenta, idPlato, cantidad) VALUES(?, ?, ?)");
		conexion.preparedStatement().setInt(1, detalleventa.getIdVenta());
		conexion.preparedStatement().setInt(2, detalleventa.getIdPlato());
		conexion.preparedStatement().setInt(3, detalleventa.getCantidad());
		conexion.CUD();
	}

	public void search(DetalleVenta detalleventa) throws Throwable {
		ResultSet rs;

		conexion.SQL("SELECT * FROM DETALLEVENTA WHERE idDetalleVenta=?");
		conexion.preparedStatement().setInt(1, detalleventa.getIdDetalleVenta());

		rs = conexion.resultSet();

		while (rs.next()) {
			detalleventa.setIdVenta(rs.getInt("idVenta"));
			detalleventa.setIdPlato(rs.getInt("idPlato"));
			detalleventa.setCantidad(rs.getInt("cantidad"));
		}

		rs.close();
	}

	public void update(DetalleVenta detalleventa) throws Throwable {
		if(detalleventa != null) {
			int idDetalleVenta = detalleventa.getIdDetalleVenta();

			conexion.SQL("UPDATE DetalleVenta SET idVenta = ?, idPlato = ?, cantidad = ? WHERE idDetalleVenta = ?");
			conexion.preparedStatement().setInt(1, detalleventa.getIdDetalleVenta());
			conexion.preparedStatement().setInt(2, detalleventa.getIdPlato());
			conexion.preparedStatement().setInt(3, detalleventa.getCantidad());
			conexion.preparedStatement().setInt(4, idDetalleVenta);
			conexion.CUD();
		}
	}

}