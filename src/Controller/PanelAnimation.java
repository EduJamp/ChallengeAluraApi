/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Coins;
import View.ConverterFinal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class PanelAnimation extends JPanel implements ActionListener{
    //Cramos nestras variables
    //creamos un array para guardar todos los fotogramas(imagenes)
    private Image[] frames; // frames --> fotogramas
    
    //Creamos una constante con el numero total de fotogramas que usaremos
    private final int TOTAL_FRAMES = 14;
    
    //indice del fotograma que se esta usando actualmente
    private int currentIndex;
    
    //posicion x e y donde se dibujara la imagen
    private int positionX;
    private int positionY;
    
    //motor de la animacion un Timer de java swing
    private Timer timer;
    
    //velocidad de la animacion
    private final int DELAY_ANIMATION = 5000 / TOTAL_FRAMES; //milisegundos entre fotogramas
    
    //Dimensiones que deseamos que tenga nuestro panel
    private final int WIDTH_PANEL = 648;
    private final int HEIGHT_PANEL = 486;
    
    //agregamos un progressbar
    private JProgressBar state;
    
    //agregamos un label para mostrar los mensajes de los estados de los porcentajes
    private JLabel messageLabel;

    public PanelAnimation(){
        
        setLayout(new BorderLayout()); //ayuda a ubicar los componentes
        
        //configuracion del panel
        setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        setBackground(new Color(0,0,0));
        
        //2. Llamamos al metodo para imprimir las imagenes
        showImages();
        
        // 3. Inicializar las variables de estado
        currentIndex = 0;
        positionX = 10;
        positionY = 100;

        
        //4. Creamos el label para los mensajes
        messageLabel = new JLabel("Iniciando Carga....");
        messageLabel.setForeground(Color.WHITE); //letras blancas
        messageLabel.setHorizontalAlignment(SwingUtilities.CENTER); //texto centrado
        
        //5. Creamos el progressbar
        state = new JProgressBar();
        state.setMaximum(TOTAL_FRAMES);
        state.setValue(0);
        state.setStringPainted(true);
        state.setForeground(Color.GREEN); //cambia el color del progreso(la barra que avanza)
        state.setBackground(Color.BLACK); //cambia el color del fondo de la barra
        
        //6. Creamos un panel inferior para agrupar el label con el progressbar
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setOpaque(false); //para que el panel sea transparente
        southPanel.add(messageLabel, BorderLayout.NORTH); //etiqueta arriba
        southPanel.add(state, BorderLayout.CENTER);//barra abajo
        
        //7. Añadimos el panel al sur del panel pricipal
        add(southPanel, BorderLayout.SOUTH);
        
        // 8. Configurar y arrancar el Timer
        timer = new Timer(DELAY_ANIMATION, this);
        timer.start();
    }
    
    //Metodo para cargar las imagenes del array
    private void showImages() {
        frames = new Image[TOTAL_FRAMES];//colocamos el tamaño al array
        
        //algoritmo para recorrer el array y mostrar las imagenes
        for (int i = 0; i < TOTAL_FRAMES; i++) {
            String nameFile = "/AnimationImages/Frame" + (i + 1) + ".png";//construimos el nombre del archivo
            
            try {
                
                Image img = new ImageIcon(getClass().getResource(nameFile)).getImage();//cargamos la imagen usand ImageIcon
                frames[i] = img;//pasamos las imagenes al array frame en orden
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cargar imagen: " + nameFile, "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    //metodo que sobreescriremos de la interfaz actionlistener
    //este metodo se llama automaticamente cada vez que el Timer se dispara(cada 150 milisegundos en este caso)
    @Override
    public void actionPerformed(ActionEvent e) {
        //actualizamos el estado de la animacion
        //codigo por si queremos que las imagenes se muevan hacia la derecha
        //empieza
        //positionX += 10;
        
        //si se sale de la pantalla que vuelva a iniciar desde la izquierda
        //if(positionX > WIDTH_PANEL) {
          //  positionX -=100;//le restamos 100 para que aparezca desde fuera
        //} //termina
        
        //avanzamos al siguiente fotograma(imagen)
        currentIndex ++;
        
        //si llegamos al final del array, regresamos al inicio para volver a empezar a imprimir el movimiento
        if(currentIndex >= TOTAL_FRAMES) {
            
            state.setValue(TOTAL_FRAMES);
            messageLabel.setText("<- Completed ->");
            
           // currentIndex = 0; //reiniciamos el indice
           timer.stop();
           
           //instanciamos y hacemos visible la otra ventana para que al terminar la animacion se cambie de ventana
           //new ConverterFinal().setVisible(true);
           ConverterFinal cf = new ConverterFinal();
           cf.setVisible(true);
           cf.setLocationRelativeTo(null);
           
           //obtennemos y cerramos la ventana actual
           Window windowAnimation = SwingUtilities.getWindowAncestor(this);//obtenemos la ventana
           windowAnimation.dispose();//cerramos la ventana
           
        }else {
            
            state.setValue(currentIndex); // en curso
            updateStatusMessage(currentIndex); // llamamos al metodo para actualizar los mensajes
            
            //hacemos que se dibuje el siguiente fotograma en pantalla si es que aun no hemos llegado al final
            repaint();
            
        }
        
        //2. Pedirle a Swing que repinte el panel
        // Esto NO dibuja inmediatamente, solo agenda un redibujado.
        // El sistema llamará a paintComponent() cuando pueda.
        
       // repaint();
    }
    
    private void updateStatusMessage(int currentFrame) {
        //como tenemos 14 imagenes podemos manejar las imagenes clave 1-4-7-11
        switch (currentFrame) {
            case 1:
                messageLabel.setText("Starting components.....");
                break;
                
            case 4:
                messageLabel.setText("Loading conversion modules.....");
                break;
                
            case 7:
                messageLabel.setText("Checking exchange rates.....");
                break;
            
            case 11:
                messageLabel.setText("Almost ready.....");
                break;
                
            default:
                break;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        //Llama primero al método del padre (esencial para limpiar el panel)
        super.paintComponent(g);
        
        // Obtiene la imagen que toca dibujar en este fotograma
        Image currentImage = frames[currentIndex];
        
        //dibujamos las imagenes con su posicion ya establecida(su tamaño original)
        if(currentImage != null) {
            
            //dibujamos la imagen para ocupar todo el ancho y alto del Panel;
            //argumentos
            //1 -> colocamos la imagen a imprimir
            //2 -> colocamos la posicion de destino en x
            //3 -> colocamos la posicion de destino en y
            //4 -> ancho del panel
            //5 -> alto del panel
            //6 -> referencia a la imagen en este caso es this
            g.drawImage(currentImage, 0, 0, WIDTH_PANEL, HEIGHT_PANEL, this);
            
        }else {
            
            //en caso la imagen no se pueda cargar
            g.setColor(Color.red);
            
            //Centrando el texto de error original
            String errorMessage = "Error al cargar la imagen" + currentIndex;
            int errorWidth = g.getFontMetrics().stringWidth(errorMessage);
            int positionMessageX = ( WIDTH_PANEL - errorWidth ) / 2;
            int positionMessageY = (HEIGHT_PANEL - g.getFontMetrics().getHeight()) / 2;
            
            //imprimimos el mensaje
            g.drawString(errorMessage, positionMessageX, positionMessageY);
            
        }
    }
}

//        // Llama primero al método del padre (esencial para limpiar el panel)
//        super.paintComponent(g);
//
//        // Obtiene la imagen que toca dibujar en este fotograma
//        Image currentImage = frames[currentIndex];
//
//        // Dibuja la imagen actual en la posición (x_pos, y_pos)
//        // La dibuja con su "dimensión ya establecida" (su tamaño original)
//        if (currentImage != null) {
//            g.drawImage(currentImage, positionX, positionY, this);
//        } else {
//            // En caso de que la imagen no se haya podido cargar
//            g.setColor(Color.RED);
//            g.drawString("Error al cargar fotograma " + currentIndex, positionX, positionY);
//        }