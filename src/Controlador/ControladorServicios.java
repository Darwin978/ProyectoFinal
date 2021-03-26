/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.ModeloServicios;
import Modelo.Servicios;
import Vista.VistaServicios;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;

/**
 *
 * @author MarcoVi
 */
public class ControladorServicios {

    private ModeloServicios modelo;
    private VistaServicios vista;

    public ControladorServicios(ModeloServicios modelo, VistaServicios vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void Iniciar_Control() {
        cargaLista("");
        vista.getBtnGuardar().addActionListener(al -> grabarServicio());
        vista.getBtnActualizar().addActionListener(al -> Rellenar_Dialogo_Editar());
        vista.getBtnGuardar_Act().addActionListener(al -> Editar_Servicio());
        vista.getBtnEliminar().addActionListener(al -> Eliminar_Servicio());

        KeyListener kc = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char codigo = ke.getKeyChar();
                if ((codigo < '0' | codigo > '9') | (vista.getTxtCodigo().getText().length() >= 4)) {
                    ke.consume();
                }
//                char Nombres_S = ke.getKeyChar();
//               if (((Nombres_S < 'a' | Nombres_S > 'z') & (Nombres_S < 'A' | Nombres_S > 'Z') & (Nombres_S!=45)& (Nombres_S != KeyEvent.VK_SPACE)) | (vista.getTxtNombre().getText().length() >= 50)) {
//                    ke.consume();
//                }
//                 char Descripcion = ke.getKeyChar();
//                if (((Descripcion < 'a' | Descripcion > 'z') & (Descripcion < 'A' | Descripcion > 'Z') & (Descripcion < '0' | Descripcion > '9') & (Descripcion != 45) & (Descripcion != KeyEvent.VK_SPACE)) | (vista.getTxtDescripcion().getText().length() >= 100)) {
//                    ke.consume();
//                }
//                char Precio = ke.getKeyChar();
//            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtPrecio().getText().length() >= 5)) {
//                ke.consume();
//            }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtBuscar().getText());
            }

        };
        KeyListener kca = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char codigo = ke.getKeyChar();
                if ((codigo < '0' | codigo > '9') | (vista.getTxtCod_Act().getText().length() >= 4)) {
                    ke.consume();
                }
//                char Nombres_S = ke.getKeyChar();
//               if (((Nombres_S < 'a' | Nombres_S > 'z') & (Nombres_S < 'A' | Nombres_S > 'Z') & (Nombres_S!=45)& (Nombres_S != KeyEvent.VK_SPACE)) | (vista.getTxtNombre().getText().length() >= 50)) {
//                    ke.consume();
//                }
//                 char Descripcion = ke.getKeyChar();
//                if (((Descripcion < 'a' | Descripcion > 'z') & (Descripcion < 'A' | Descripcion > 'Z') & (Descripcion < '0' | Descripcion > '9') & (Descripcion != 45) & (Descripcion != KeyEvent.VK_SPACE)) | (vista.getTxtDescripcion().getText().length() >= 100)) {
//                    ke.consume();
//                }
//                char Precio = ke.getKeyChar();
//            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtPrecio().getText().length() >= 5)) {
//                ke.consume();
//            }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtBuscar().getText());
            }

        };
        KeyListener kn = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
               
                char Nombres_S = ke.getKeyChar();
               if (((Nombres_S < 'a' | Nombres_S > 'z') & (Nombres_S < 'A' | Nombres_S > 'Z') & (Nombres_S!=45)& (Nombres_S != KeyEvent.VK_SPACE)) | (vista.getTxtNombre().getText().length() >= 50)) {
                    ke.consume();
                }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kna = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
               
                char Nombres_S = ke.getKeyChar();
               if (((Nombres_S < 'a' | Nombres_S > 'z') & (Nombres_S < 'A' | Nombres_S > 'Z') & (Nombres_S!=45)& (Nombres_S != KeyEvent.VK_SPACE)) | (vista.getTxtNombre_Act().getText().length() >= 50)) {
                    ke.consume();
                }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kd = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                 char Descripcion = ke.getKeyChar();
                if (((Descripcion < 'a' | Descripcion > 'z') & (Descripcion < 'A' | Descripcion > 'Z') & (Descripcion < '0' | Descripcion > '9') & (Descripcion != 45) & (Descripcion != KeyEvent.VK_SPACE)) | (vista.getTxtDescripcion().getText().length() >= 250)) {
                    ke.consume();
                }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kda = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                 char Descripcion = ke.getKeyChar();
                if (((Descripcion < 'a' | Descripcion > 'z') & (Descripcion < 'A' | Descripcion > 'Z') & (Descripcion < '0' | Descripcion > '9') & (Descripcion != 45) & (Descripcion != KeyEvent.VK_SPACE)) | (vista.getTxtDescrip_Act().getText().length() >= 250)) {
                    ke.consume();
                }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kp = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            char Precio = ke.getKeyChar();
            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtPrecio().getText().length() >= 5)) {
                ke.consume();
            }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kpa = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            char Precio = ke.getKeyChar();
            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtPrecio_Act().getText().length() >= 5)) {
                ke.consume();
            }
              }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
         };
        KeyListener kb = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
//                   char codigo = ke.getKeyChar();
//                if ((codigo < '0' | codigo > '9') | (vista.getTxtCodigo().getText().length() >= 4)) {
//                    ke.consume();
//                }
////                char Nombres_S = ke.getKeyChar();
////               if (((Nombres_S < 'a' | Nombres_S > 'z') & (Nombres_S < 'A' | Nombres_S > 'Z') & (Nombres_S!=45)& (Nombres_S != KeyEvent.VK_SPACE)) | (vista.getTxtNombre().getText().length() >= 50)) {
////                    ke.consume();
////                }
//                 char Descripcion = ke.getKeyChar();
//                if (((Descripcion < 'a' | Descripcion > 'z') & (Descripcion < 'A' | Descripcion > 'Z') & (Descripcion < '0' | Descripcion > '9') & (Descripcion != 45) & (Descripcion != KeyEvent.VK_SPACE)) | (vista.getTxtDescripcion().getText().length() >= 100)) {
//                    ke.consume();
//                }
//                char Precio = ke.getKeyChar();
//            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtPrecio().getText().length() >= 5)) {
//                ke.consume();
//            }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtBuscar().getText());
            }

        };
        
            vista.getTxtNombre().addKeyListener(kn);
            vista.getTxtBuscar().addKeyListener(kb);
            vista.getTxtCodigo().addKeyListener(kc);
            vista.getTxtDescripcion().addKeyListener(kd);
            vista.getTxtDescrip_Act().addKeyListener(kda);
            vista.getTxtPrecio().addKeyListener(kp);
            vista.getTxtCod_Act().addKeyListener(kca);
            vista.getTxtNombre_Act().addKeyListener(kna);
            vista.getTxtPrecio_Act().addKeyListener(kpa);
        
    }

    
    private void grabarServicio() {
        
        if ( vista.getTxtCodigo().getText().equals("") || vista.getTxtNombre().getText().equals("")|| vista.getTxtDescripcion().getText().equals("")|| vista.getTxtPrecio().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODAS LAS CASILLAS");
        }
        else if (validar_codigo() == false) {
            JOptionPane.showMessageDialog(null, "EL CODIGO DE SERVICIO YA EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String codigo = vista.getTxtCodigo().getText();
            String nombres = vista.getTxtNombre().getText();
            String desc = vista.getTxtDescripcion().getText();
            Double precio = Double.parseDouble(vista.getTxtPrecio().getText());

            ModeloServicios ser = new ModeloServicios(codigo, nombres, desc, precio);
            if (ser.Crear_Servicio()) {
                JOptionPane.showMessageDialog(vista, "Registro grabado Satisfactoriamente");
                cargaLista("");

            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
        }

    }

    private void cargaLista(String aguja) {

        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTablaServicios().getModel();
        tblModel.setNumRows(0);
        List<Servicios> lista = ModeloServicios.Listar(aguja);
        int ncols = tblModel.getColumnCount();

        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(p1 -> {

            tblModel.addRow(new Object[ncols]);
            vista.getTablaServicios().setValueAt(p1.getCodigo_ser(), i.value, 0);

            vista.getTablaServicios().setValueAt(p1.getNombre_ser(), i.value, 1);
            vista.getTablaServicios().setValueAt(p1.getDescripcion(), i.value, 2);
            vista.getTablaServicios().setValueAt(p1.getPrecio(), i.value, 3);

            i.value++;
            ;
        });
    }

    //ELIMINAR DE LA TABLA-BASE
    private void Eliminar_Servicio() {
        String idSeleccion = "";
        int fila = vista.getTablaServicios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTablaServicios();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
            ModeloServicios persona = new ModeloServicios(idSeleccion);
            if (persona.Eliminar_Servicio()) {
                cargaLista("");
                JOptionPane.showMessageDialog(vista, "El registro ha sido eliminado");
            } else {
                System.out.println("Sin eliminar");
            }
        }

    }

    //SIRVE PARA RECUPERAR DATOS Y MANDAR A TXT
    public void Rellenar_Dialogo_Editar() {
        String idSeleccion = "";
        int fila = vista.getTablaServicios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTablaServicios();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
        }

        ModeloServicios servicio = new ModeloServicios(idSeleccion);
        List<Servicios> lista = servicio.Buscar_Servicio();
        for (int i = 0; i < lista.size(); i++) {
            Servicios ser = lista.get(i);

            String codigo = ser.getCodigo_ser();
            String nombre = ser.getNombre_ser();
            String desc = ser.getDescripcion();
            Double precio = ser.getPrecio();

            vista.getjDialog1().setVisible(true);

            vista.getjDialog1().setTitle("EDITAR SERVICIO");

            vista.getjDialog1().setLocationRelativeTo(null);
            vista.getTxtCod_Act().setText(codigo);
            vista.getTxtNombre_Act().setText(nombre);
            vista.getTxtDescrip_Act().setText(desc);
            vista.getTxtPrecio_Act().setText(precio + "");

        }
    }

    //SIRVE PARA EDITAR
    private void Editar_Servicio() {
        String codigo = vista.getTxtCod_Act().getText();
        String nombre = vista.getTxtNombre_Act().getText();
        String desc = vista.getTxtDescrip_Act().getText();
        Double precio = Double.parseDouble(vista.getTxtPrecio_Act().getText());

        ModeloServicios ser = new ModeloServicios(codigo, nombre, desc, precio);

        if (ser.Editar_Servicio()) {
            JOptionPane.showMessageDialog(vista, "Registro Modificado");
            cargaLista("");
            vista.getjDialog1().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR");
        }
    }

    //validar si existen datos
    public boolean validar_codigo() {
        Conexion conecta = new Conexion();
        String sql = "SELECT codigo FROM servicios  WHERE codigo ='" + vista.getTxtCodigo().getText() + "'";
        ResultSet rs = conecta.query(sql);
        try {
            if (rs.next() == true) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

  
 
}
