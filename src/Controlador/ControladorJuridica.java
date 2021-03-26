/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.ModeloJuridica;
import Modelo.Persona_Juridica;
import Vista.VistaPersonaJuridica;
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
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author MarcoVi
 */
public class ControladorJuridica {
    Conexion conecta = new Conexion();
    private ModeloJuridica modelo;
    private VistaPersonaJuridica vista;
    
    public ControladorJuridica(ModeloJuridica modelo,VistaPersonaJuridica vista){
    this.modelo=modelo;
    this.vista=vista;
    vista.setVisible(true);
    }
      public void Iniciar_Control() {
          cargaLista("");
          vista.getBtnGrabar().addActionListener(al -> grabarPersona());
           
           vista.getBtneliminar().addActionListener(al->Eliminar_Juridica());
            vista.getBtnActualizar().addActionListener(al->Rellenar_Dialogo_Editar());
          vista.getBtnactualizar_act().addActionListener(al->Editar_Juridica());
            
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }
        };
        vista.getTxtbuscar().addKeyListener(kl);
        

          vista.getTxtruc().addKeyListener(krj);
          vista.getTxtnombres().addKeyListener(knj);
          vista.getTxtapellidos().addKeyListener(kaj);
          vista.getTxtrazon().addKeyListener(krsj);
          vista.getTxtdireccion().addKeyListener(kdj);
          vista.getTxtcorreo().addKeyListener(kcorj);
          vista.getTxtcelular().addKeyListener(kcelj);
          vista.getTxttelefono().addKeyListener(ktj);
          vista.getTxtsocios().addKeyListener(ksj);
            
        
      }

    private void grabarPersona() {
        if ( vista.getTxtcodigo().getText().equals("")||vista.getTxtruc().getText().equals("")||vista.getTxtnombres().getText().equals("")
                ||vista.getTxtapellidos().getText().equals("")||vista.getTxtrazon().getText().equals("")
                ||vista.getTxtdireccion().getText().equals("")||vista.getTxtcorreo().getText().equals("")
                ||vista.getTxttelefono().getText().equals("")||vista.getTxtcelular().getText().equals("")
                ||vista.getTxtusuario().getText().equals("")||vista.getTxtclave().getText().equals("")||
                vista.getTxtcorreo().getText().equals("")||vista.getTxtsocios().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODAS LAS CASILLAS");
        }
    else if (validar_codigo() == false) {
            JOptionPane.showMessageDialog(null, "EL CODIGO DEL LA PERSONA JURIDICA YA EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else if (validar_cedula() == false) {
            JOptionPane.showMessageDialog(null, "LA CEDULA DE LA PERSONA JURIDICA YA EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String razon = vista.getTxtrazon().getText();
            String codigo = vista.getTxtcodigo().getText();
            int socios = Integer.parseInt(vista.getTxtsocios().getText());
            String dni = vista.getTxtruc().getText();
            String nombres = vista.getTxtnombres().getText();
            String apellidos = vista.getTxtapellidos().getText();
            String dire = vista.getTxtdireccion().getText();
            String correo = vista.getTxtcorreo().getText();
            String tele = vista.getTxttelefono().getText();
            String celular = vista.getTxtcelular().getText();
            String usuario = vista.getTxtusuario().getText();
            String clave = vista.getTxtclave().getText();

            ModeloJuridica juridica = new ModeloJuridica(razon, socios, codigo, dni, nombres, apellidos, dire, correo, tele, celular, usuario, clave);
            if (juridica.Crear_Persona_Juridica()) {
                JOptionPane.showMessageDialog(vista, "Registro grabado Satisfactoriamente");
                cargaLista("");
            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }

        }
    }

      
   private void cargaLista(String aguja){
        
        
//        vista.getTabla_juridica().setDefaultRenderer(Object.class, new ImagenTabla());
//        vista.getTabla_juridica().setRowHeight(100);
//        DefaultTableCellRenderer renderer= new DefaultTableCellHeaderRenderer();
        
        DefaultTableModel tblModel;
        tblModel=(DefaultTableModel)vista.getTabla_juridica().getModel();
        tblModel.setNumRows(0);
        List<Persona_Juridica> lista=ModeloJuridica.Listar(aguja);
        int ncols=tblModel.getColumnCount();

        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(p1->{
             
           tblModel.addRow(new Object[ncols]);
           vista.getTabla_juridica().setValueAt(p1.getCodigo(), i.value , 0);
           vista.getTabla_juridica().setValueAt(p1.getDNI(), i.value , 1);
            vista.getTabla_juridica().setValueAt(p1.getNombres(), i.value , 2);
            vista.getTabla_juridica().setValueAt(p1.getApellidos(), i.value , 3);
            vista.getTabla_juridica().setValueAt(p1.getRazon_social(), i.value , 4);
            vista.getTabla_juridica().setValueAt(p1.getDireccion(), i.value , 5);
            vista.getTabla_juridica().setValueAt(p1.getCorreo(), i.value , 6);
            vista.getTabla_juridica().setValueAt(p1.getTelefono(), i.value , 7);
            vista.getTabla_juridica().setValueAt(p1.getCelular(), i.value , 8);
            vista.getTabla_juridica().setValueAt(p1.getUsuario(), i.value, 9);
            vista.getTabla_juridica().setValueAt(p1.getClave(), i.value, 10);
            vista.getTabla_juridica().setValueAt(p1.getSocios(), i.value, 11);
            
            
           
//            //completar datos
//           Image img = p1.getFoto();
//           if(img!=null){
//                Image newimg = img.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
//                ImageIcon icon = new ImageIcon(newimg);
//                renderer.setIcon(icon);
//                vista.getTabla_juridica().setValueAt(new JLabel(icon), i.value, 9);
//           }else{
//               vista.getTabla_juridica().setValueAt(null, i.value, 9);
//           }
           i.value++;
          ;
        });
    }

   
   //ELIMINAR DE LA TABLA-BASE
    private void Eliminar_Juridica() {
        String idSeleccion = "";
        int fila = vista.getTabla_juridica().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTabla_juridica();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
            ModeloJuridica persona = new ModeloJuridica(idSeleccion);
            if (persona.Eliminar_Juridica()) {
                cargaLista("");
                JOptionPane.showMessageDialog(vista, "El registro ha sido eliminado");
            } else {
                System.out.println("Sin eliminar");
            }
        }

    }
   
   
    public void Rellenar_Dialogo_Editar() {
        String idSeleccion = "";
        int fila = vista.getTabla_juridica().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            JTable tabla = vista.getTabla_juridica();
            idSeleccion = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
        }

        ModeloJuridica persona = new ModeloJuridica(idSeleccion);
        List<Persona_Juridica> lista = persona.Buscar_Juridica();
        
        for (int i = 0; i < lista.size(); i++) {
            Persona_Juridica ser= lista.get(i);
            String codigo=ser.getCodigo();
            String cedula=ser.getDNI();
            String nombres=ser.getNombres();
            String apellidos=ser.getApellidos();
            String razon=ser.getRazon_social();
            String direccion=ser.getDireccion();
            String correo=ser.getCorreo();
            String telefono=ser.getTelefono();
            String celular=ser.getCelular();
            
            String usuario=ser.getUsuario();
            String clave=ser.getClave();
            int socio=ser.getSocios();

            
            vista.getDialogo_Juridica().setVisible(true);
            
            vista.getDialogo_Juridica().setTitle("EDITAR PERSONA");
            vista.getDialogo_Juridica().setSize(525, 525);
            
            vista.getDialogo_Juridica().setLocationRelativeTo(null);
            vista.getTxtcodigo_act().setText(codigo);
            vista.getTxtruc_act().setText(cedula);
            vista.getTxtnombres_act().setText(nombres);
            vista.getTxtapellidos_act().setText(apellidos);
            vista.getTxtrazon_act().setText(razon);
            vista.getTxtdireccion_act().setText(direccion);
            vista.getTxtcorreo_act().setText(correo);
            vista.getTxtTelefono_act().setText(telefono);
            vista.getTxtCelular_act().setText(celular);
            vista.getTxtUsuario_act().setText(usuario);
            vista.getTxtClave_act().setText(clave);
            vista.getTxtsocios_act().setText(socio+"");
        }
    }
    //SIRVE PARA EDITAR
      private void Editar_Juridica() {
          System.out.println("editar");
          String codigo=vista.getTxtcodigo_act().getText();
          String cedula=vista.getTxtruc_act().getText();
          String nombres=vista.getTxtnombres_act().getText();
          String apellidos=vista.getTxtapellidos_act().getText();
          String razon=vista.getTxtrazon_act().getText();
          String direccion=vista.getTxtdireccion_act().getText();
          String correo=vista.getTxtcorreo_act().getText();
          String telefono=vista.getTxtTelefono_act().getText();
          String celular=vista.getTxtCelular_act().getText();
          
          String usuario=vista.getTxtUsuario_act().getText();
          String clave=vista.getTxtClave_act().getText();
          int socios= Integer.parseInt(vista.getTxtsocios_act().getText());
   
        
        
       

        ModeloJuridica empleado=new ModeloJuridica(razon,socios,codigo,cedula,nombres,apellidos,direccion,correo,telefono,celular,usuario,clave);
        
        
       
        if (empleado.Editar_Juridica()) {
            JOptionPane.showMessageDialog(vista, "Registro Modificado");
            cargaLista("");
            vista.getDialogo_Juridica().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR metodo editar");
        }
    }
    
          //validar si existen codigo
    public boolean validar_codigo() {
        String sql = "SELECT codigo FROM persona_juridica  WHERE codigo ='" + vista.getTxtcodigo().getText() + "'";
        ResultSet rs = conecta.query(sql);
        try {
            if (rs.next() == true) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Juridica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    //validar si existen cedula 
    public boolean validar_cedula() {
        String sql = "SELECT ruc FROM persona_juridica  WHERE ruc='" + vista.getTxtruc().getText() + "'";
        ResultSet rs = conecta.query(sql);
        try {
            if (rs.next() == true) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Juridica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    KeyListener krj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char ruc_jur = ke.getKeyChar();
                if ((ruc_jur < '0' | ruc_jur > '9') | (vista.getTxtruc().getText().length() >= 13)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
    
    KeyListener knj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                char nom_jur = ke.getKeyChar();
               if (((nom_jur < 'a' | nom_jur > 'z') & (nom_jur < 'A' | nom_jur > 'Z') & (nom_jur!=45)& (nom_jur != KeyEvent.VK_SPACE)) | (vista.getTxtnombres().getText().length() >= 50)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
    
    KeyListener kaj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                char ape_jur = ke.getKeyChar();
               if (((ape_jur < 'a' | ape_jur > 'z') & (ape_jur < 'A' | ape_jur > 'Z') & (ape_jur!=45)& (ape_jur != KeyEvent.VK_SPACE)) | (vista.getTxtapellidos().getText().length() >= 50)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
    
    KeyListener krsj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                char razon = ke.getKeyChar();
               if (((razon < 'a' | razon > 'z') & (razon < 'A' | razon > 'Z') & (razon!=45)& (razon != KeyEvent.VK_SPACE)) | (vista.getTxtrazon().getText().length() >= 150)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
    
    KeyListener kdj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                char dir_jur = ke.getKeyChar();
               if (((dir_jur < 'a' | dir_jur > 'z') & (dir_jur < 'A' | dir_jur > 'Z') & (dir_jur!=45)& (dir_jur != KeyEvent.VK_SPACE)) | (vista.getTxtdireccion().getText().length() >= 150)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
   
    KeyListener kcorj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char correo_jur = ke.getKeyChar();
        if (((correo_jur < 'a' | correo_jur > 'z') & (correo_jur < 'A' | correo_jur > 'Z') & (correo_jur < '0' | correo_jur > '9') & (correo_jur != 64) & (correo_jur != 46)) | (vista.getTxtcorreo().getText().length() >= 80)) {
            ke.consume();
        }
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
   
   KeyListener ktj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char tel_jur = ke.getKeyChar();
                if ((tel_jur < '0' | tel_jur > '9') | (vista.getTxttelefono().getText().length() >= 9)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
   
   KeyListener kcelj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char celular = ke.getKeyChar();
                if ((celular < '0' | celular > '9') | (vista.getTxtcelular().getText().length() >= 10)) {
                    ke.consume();
                }
            
            }
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
   
   
   KeyListener ksj = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
                   char socios = ke.getKeyChar();
                if ((socios < '0' | socios > '9') | (vista.getTxtsocios().getText().length() >= 5)) {
                    ke.consume();
                }
            
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                cargaLista(vista.getTxtbuscar().getText());
            }

        };
    
}
