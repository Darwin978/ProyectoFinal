/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Empleado;
import Modelo.ModeloEmpleado;
import Vista.VistaGestionEmpleado;
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
public class ControladorEmpleado {
     Conexion conecta = new Conexion();
    private ModeloEmpleado modelo;
    private VistaGestionEmpleado vista;

    public ControladorEmpleado(ModeloEmpleado modelo, VistaGestionEmpleado vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void Iniciar_Control() {
        cargaLista("");

        vista.getBtnGuardar1().addActionListener(al -> grabarEmpleado());
        vista.getBtnActualizar().addActionListener(al -> Rellenar_Dialogo_Editar());
        vista.getBtnGrabar_Act().addActionListener(al -> Editar_Empleado());
        vista.getBtnEliminar().addActionListener(al -> Eliminar_Empleado());

        KeyListener kb = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtBusqueda().getText());
            }

        };
        KeyListener kn = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtNombre().getText().length() >= 60)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kna = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtNombres_Act().getText().length() >= 60)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kap = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtApellido().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kapa = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtApellidos_Act().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kd = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
              char Direccion_PN = ke.getKeyChar();
        if (((Direccion_PN < 'a' | Direccion_PN > 'z') & (Direccion_PN < 'A' | Direccion_PN > 'Z') & (Direccion_PN < '0' | Direccion_PN > '9') & (Direccion_PN != 45) && (Direccion_PN != KeyEvent.VK_SPACE)) | (vista.getTxtDireccion().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kda = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
              char Direccion_PN = ke.getKeyChar();
        if (((Direccion_PN < 'a' | Direccion_PN > 'z') & (Direccion_PN < 'A' | Direccion_PN > 'Z') & (Direccion_PN < '0' | Direccion_PN > '9') & (Direccion_PN != 45) && (Direccion_PN != KeyEvent.VK_SPACE)) | (vista.getTxtDireccion_Act().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kc = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                 char Correo_PN = ke.getKeyChar();
        if (((Correo_PN < 'a' | Correo_PN > 'z') & (Correo_PN < 'A' | Correo_PN > 'Z') & (Correo_PN < '0' | Correo_PN > '9') & (Correo_PN != 64) & (Correo_PN != 46)) | (vista.getTxtCorreo().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kca = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                 char Correo_PN = ke.getKeyChar();
        if (((Correo_PN < 'a' | Correo_PN > 'z') & (Correo_PN < 'A' | Correo_PN > 'Z') & (Correo_PN < '0' | Correo_PN > '9') & (Correo_PN != 64) & (Correo_PN != 46)) | (vista.getTxtCorreo_Desc().getText().length() >= 80)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kt = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
           char Telefono_PN = ke.getKeyChar();
        if ((Telefono_PN < '0' | Telefono_PN > '9') | (vista.getTxtTelefono().getText().length() >= 9)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kta = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
           char Telefono_PN = ke.getKeyChar();
        if ((Telefono_PN < '0' | Telefono_PN > '9') | (vista.getTxtTelefono_Act().getText().length() >= 9)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kce = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Celular_PN = ke.getKeyChar();
        if ((Celular_PN < '0' | Celular_PN > '9') | (vista.getTxtCelular().getText().length() >= 10)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kcea = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Celular_PN = ke.getKeyChar();
        if ((Celular_PN < '0' | Celular_PN > '9') | (vista.getTxtCelular_Act().getText().length() >= 10)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kcar = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtCargo().getText().length() >= 60)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener kcara = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char Nombres_E = ke.getKeyChar();
        if (((Nombres_E < 'a' | Nombres_E > 'z') & (Nombres_E < 'A' | Nombres_E > 'Z') && (Nombres_E != KeyEvent.VK_SPACE)) | (vista.getTxtCargo_Act().getText().length() >= 60)) {
            ke.consume();
        }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }

        };
        KeyListener ks = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            char Precio = ke.getKeyChar();
            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtSueldo().getText().length() >= 7)) {
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
        KeyListener ksa = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            char Precio = ke.getKeyChar();
            if ((Precio < '0' | Precio > '9') &(Precio!=46) | (vista.getTxtSueldo_ACT().getText().length() >= 7)) {
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
        vista.getTxtBusqueda().addKeyListener(kb);
        vista.getTxtNombre().addKeyListener(kn);
        vista.getTxtNombres_Act().addKeyListener(kna);
        vista.getTxtApellido().addKeyListener(kapa);
        vista.getTxtDireccion().addKeyListener(kd);
        vista.getTxtDireccion_Act().addKeyListener(kda);
        vista.getTxtCorreo().addKeyListener(kc);
        vista.getTxtCorreo_Desc().addKeyListener(kca);
        vista.getTxtTelefono().addKeyListener(kt);
        vista.getTxtTelefono_Act().addKeyListener(kta);
        vista.getTxtCelular().addKeyListener(kce);
        vista.getTxtCelular_Act().addKeyListener(kcea);
        vista.getTxtCargo().addKeyListener(kcar);
        vista.getTxtCargo_Act().addKeyListener(kcara);
        vista.getTxtSueldo().addKeyListener(ks);
        vista.getTxtSueldo_ACT().addKeyListener(ksa);

    }

    private void grabarEmpleado() {
        if(vista.getTxtCodigo().getText().equals("")|| vista.getTxtCedula1().getText().equals("")||vista.getTxtNombre().getText().equals("")|| vista.getTxtApellido().getText().equals("") 
                || vista.getTxtDireccion().getText().equals("")||vista.getTxtCorreo().getText().equals("")
                ||vista.getTxtTelefono().getText().equals("")|| vista.getTxtCelular().getText().equals("")
                ||vista.getTxtCargo().getText().equals("")|| vista.getTxtSueldo().getText().equals("")
                ||vista.getTxtUsuario().getText().equals("")||vista.getTxtClave().getText().equals("")){
            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODAS LAS CASILLAS");
        }
        else if (validar_codigo() == false) {
            JOptionPane.showMessageDialog(null, "EL CODIGO DEL EMPLEADO YA EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else if (validar_cedula() == false) {
            JOptionPane.showMessageDialog(null, "LA CEDULA DEL EMPLEADO YA EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String codigo = vista.getTxtCodigo().getText();
            String cedula = vista.getTxtCedula1().getText();
            String nombres = vista.getTxtNombre().getText();
            String apellidos = vista.getTxtApellido().getText();
            String direccion = vista.getTxtDireccion().getText();
            String correo = vista.getTxtCorreo().getText();
            String telefono = vista.getTxtTelefono().getText();
            String celular = vista.getTxtCelular().getText();
            String cargo = vista.getTxtCargo().getText();
            Double sueldo = Double.parseDouble(vista.getTxtSueldo().getText());
            String usuario = vista.getTxtUsuario().getText();
            String clave = vista.getTxtClave().getText();

            ModeloEmpleado empleado = new ModeloEmpleado(cargo, sueldo, codigo, cedula, nombres, apellidos, direccion, correo, telefono, celular, usuario, clave);
            if (empleado.Crear_Empleado()) {
                JOptionPane.showMessageDialog(vista, "Registro grabado Satisfactoriamente");
                cargaLista("");

            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
        }

    }

    private void cargaLista(String aguja) {

//        vista.getTablaEmpleados().setDefaultRenderer(Object.class, new ImagenTabla());
//        vista.getTablaEmpleados().setRowHeight(100);
//        DefaultTableCellRenderer renderer= new DefaultTableCellHeaderRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTablaEmpleados().getModel();
        tblModel.setNumRows(0);
        List<Empleado> lista = ModeloEmpleado.Listar(aguja);
        int ncols = tblModel.getColumnCount();

        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(p1 -> {

            tblModel.addRow(new Object[ncols]);
            vista.getTablaEmpleados().setValueAt(p1.getCodigo(), i.value, 0);
            vista.getTablaEmpleados().setValueAt(p1.getDNI(), i.value, 1);
            vista.getTablaEmpleados().setValueAt(p1.getNombres(), i.value, 2);
            vista.getTablaEmpleados().setValueAt(p1.getApellidos(), i.value, 3);
            vista.getTablaEmpleados().setValueAt(p1.getDireccion(), i.value, 4);
            vista.getTablaEmpleados().setValueAt(p1.getCorreo(), i.value, 5);
            vista.getTablaEmpleados().setValueAt(p1.getTelefono(), i.value, 6);
            vista.getTablaEmpleados().setValueAt(p1.getCelular(), i.value, 7);
            vista.getTablaEmpleados().setValueAt(p1.getCargo(), i.value, 8);
            vista.getTablaEmpleados().setValueAt(p1.getSueldo(), i.value, 9);
            vista.getTablaEmpleados().setValueAt(p1.getUsuario(), i.value, 10);
            vista.getTablaEmpleados().setValueAt(p1.getClave(), i.value, 11);

//            //completar datos
//           Image img = p1.getFoto();
//           if(img!=null){
//                Image newimg = img.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
//                ImageIcon icon = new ImageIcon(newimg);
//                renderer.setIcon(icon);
//                vista.getTablaEmpleados().setValueAt(new JLabel(icon), i.value, 9);
//           }else{
//               vista.getTablaEmpleados().setValueAt(null, i.value, 9);
//           }
            i.value++;
            ;
        });
    }

    //ELIMINAR DE LA TABLA-BASE
    private void Eliminar_Empleado() {
        String idSeleccion = "";
        int fila = vista.getTablaEmpleados().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTablaEmpleados();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
            ModeloEmpleado persona = new ModeloEmpleado(idSeleccion);
            if (persona.Eliminar_Empleado()) {
                cargaLista("");
                JOptionPane.showMessageDialog(vista, "El registro ha sido eliminado");
            } else {
                System.out.println("Sin eliminar");
            }
        }

    }

    public void Rellenar_Dialogo_Editar() {
        String idSeleccion = "";
        int fila = vista.getTablaEmpleados().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTablaEmpleados();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
        }

        ModeloEmpleado persona = new ModeloEmpleado(idSeleccion);
        List<Empleado> lista = persona.Buscar_Empleado();

        for (int i = 0; i < lista.size(); i++) {
            Empleado ser = lista.get(i);
            String codigo = ser.getCodigo();
            String cedula = ser.getDNI();
            String nombres = ser.getNombres();
            String apellidos = ser.getApellidos();
            String direccion = ser.getDireccion();
            String correo = ser.getCorreo();
            String telefono = ser.getTelefono();
            String celular = ser.getCelular();
            String cargo = ser.getCargo();
            Double sueldo = ser.getSueldo();
            String usuario = ser.getUsuario();
            String clave = ser.getClave();

            vista.getDialogo_Empleado().setVisible(true);

            vista.getDialogo_Empleado().setTitle("EDITAR PERSONA");
            vista.getDialogo_Empleado().setSize(525, 525);

            vista.getDialogo_Empleado().setLocationRelativeTo(null);
            vista.getTxtCodigo_Act().setText(codigo);
            vista.getTxtCedula_Act().setText(cedula);
            vista.getTxtNombres_Act().setText(nombres);
            vista.getTxtApellidos_Act().setText(apellidos);
            vista.getTxtDireccion_Act().setText(direccion);
            vista.getTxtCorreo_Desc().setText(correo);
            vista.getTxtTelefono_Act().setText(telefono);
            vista.getTxtCelular_Act().setText(celular);
            vista.getTxtCargo_Act().setText(cargo);
            vista.getTxtSueldo_ACT().setText(sueldo + "");
            vista.getTxtUsuario_Act().setText(usuario);
            vista.getTxtClave_Act().setText(clave);
        }
    }

    //SIRVE PARA EDITAR
    private void Editar_Empleado() {
        String codigo = vista.getTxtCodigo_Act().getText();
        String cedula = vista.getTxtCedula_Act().getText();
        String nombres = vista.getTxtNombres_Act().getText();
        String apellidos = vista.getTxtApellidos_Act().getText();
        String direccion = vista.getTxtDireccion_Act().getText();
        String correo = vista.getTxtCorreo_Desc().getText();
        String telefono = vista.getTxtTelefono_Act().getText();
        String celular = vista.getTxtCelular_Act().getText();
        String cargo = vista.getTxtCargo_Act().getText();
        Double sueldo = Double.parseDouble(vista.getTxtSueldo_ACT().getText());
        String usuario = vista.getTxtUsuario_Act().getText();
        String clave = vista.getTxtClave_Act().getText();

        ModeloEmpleado empleado = new ModeloEmpleado(cargo, sueldo, codigo, cedula, nombres, apellidos, direccion, correo, telefono, celular, usuario, clave);

        if (empleado.Editar_Empleado()) {
            JOptionPane.showMessageDialog(vista, "Registro Modificado");
            cargaLista("");
            vista.getDialogo_Empleado().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR metodo editar");
        }
    }

    //validar si existen codigo
    public boolean validar_codigo() {

        String sql = "SELECT codigo FROM empleado  WHERE codigo ='" + vista.getTxtCodigo().getText() + "'";
        ResultSet rs = conecta.query(sql);
        try {
            if (rs.next() == true) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    //validar si existen cedula 
    public boolean validar_cedula() {

        String sql = "SELECT cedula FROM empleado  WHERE cedula='" + vista.getTxtCedula1().getText() + "'";
        ResultSet rs = conecta.query(sql);
        try {
            if (rs.next() == true) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    
}
