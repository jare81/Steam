/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.General;
import Codee.Juego;
import Codee.JuegoManager;
import Codee.Reproductor;
import Codee.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class BibliotecaPANEL extends JPanel {

    private General user;
    private Usuario actual;

    private JPanel informacion;
    private GridBagConstraints gridInfo;
    private GridBagConstraints gridB;
    private GridBagConstraints gridT;

    //biblio de spotify
    private String tituloM = "";
    private String artistaM = "";
    private String albumM = "";
    private String rutaM = "";
    private String imagenM = "";
    private String duracionM="";
    private int indiceM = 0;
    private ImageIcon playIcon;
    private ImageIcon pauseIcon;
    private ImageIcon favIcon;
    private ImageIcon nofavIcon;
    private boolean isPlaying = false;
    private boolean isFav;
    private Reproductor rep;
    private JPanel musica;
    private GridBagConstraints grid1;
    

    //biblio de steam
    private String nombreJ = "";
    private String generoJ = "";
    private String desarrolladorJ = "";
    private String lanzamientoJ = "";
    private String rutaJ = "";
    private String imagenJ = "";
    private int indiceJ = 0;
    private ImageIcon noDown;
    private ImageIcon down;
    private JuegoManager dev;
    private JPanel juegos;
    private GridBagConstraints grid2;

    public BibliotecaPANEL(General user) {
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);

        this.user = user;
        actual = user.getUsuarioActual();
        rep = new Reproductor();
        dev = new JuegoManager();
        

        gridB = new GridBagConstraints();

        grid1 = new GridBagConstraints();
        grid1.gridy = 0;
        grid1.gridx = 0;
        grid1.insets = new Insets(10, 10, 10, 10);

        musica = new JPanel(new GridBagLayout());
        musica.setBackground(Color.WHITE);
        musica.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        musica.setPreferredSize(new Dimension(850, 700));

        GridBagConstraints gridButtonConstraints = new GridBagConstraints();
        gridButtonConstraints.insets = new Insets(5, 5, 5, 5);
        gridButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridButtonConstraints.weightx = 1.0;
        final int d = 1;

        addButtonToPanelDMusica(musica, gridButtonConstraints, d);

        JScrollPane musicaScrollPane = new JScrollPane(musica);
        musicaScrollPane.setPreferredSize(new Dimension(900, 350)); 
        musicaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        musicaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(musicaScrollPane, grid1);

         grid2 = new GridBagConstraints();
        grid2.gridy = 1;
        grid2.gridx = 0;
        grid2.insets = new Insets(10, 10, 10, 10);

        juegos = new JPanel(new GridBagLayout());
        juegos.setBackground(Color.WHITE);
        juegos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        juegos.setPreferredSize(new Dimension(850, 400));
        final int c = 4;

        addButtonToPanelDJuegos(juegos, gridButtonConstraints, c);

        JScrollPane juegosScrollPane = new JScrollPane(juegos);
        juegosScrollPane.setPreferredSize(new Dimension(900, 350)); // Tamaño visible
        juegosScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        juegosScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(juegosScrollPane, grid2);

        informacion = new JPanel(new GridBagLayout());
        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(300, 250));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        ImageIcon icon2 = new ImageIcon(imagenM);
        Image img2 = icon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        gridF.gridy = 0;
        gridF.gridx = 0;
        informacion.add(imageLabel2);

        gridInfo = new GridBagConstraints();
        gridInfo.gridx = 1; 
        gridInfo.gridy = 0; 
        gridInfo.gridheight = 2; 
        gridInfo.fill = GridBagConstraints.VERTICAL;

        add(informacion, gridInfo); 
        informacion.setVisible(false);

    }

    private void actualizarInformacionMusica() {
        informacion.removeAll();

        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(330, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        // Agregar la imagen
        ImageIcon icon2 = new ImageIcon(imagenM);
        Image img2 = icon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        gridF.gridy = 0;
        gridF.gridx = 0;
        informacion.add(imageLabel2, gridF);

        // Crear el panel de descripción
        JPanel descripcion = new JPanel();
        descripcion.setBackground(Color.LIGHT_GRAY);
        descripcion.setLayout(new GridBagLayout());
        gridF.gridy = 1;
        gridF.gridx = 0;
        gridF.insets = new Insets(5, 5, 5, 5);
        informacion.add(descripcion, gridF);

        GridBagConstraints gridD = new GridBagConstraints();
        gridD.insets = new Insets(10, 10, 10, 10);

        // Agregar información
        gridD.gridy = 0;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Titulo: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(tituloM), gridD);

        gridD.gridy = 1;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Artista: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(artistaM), gridD);

        gridD.gridy = 2;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Album: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(albumM), gridD);

        gridD.gridy = 3;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Duracion: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(duracionM), gridD); 

        gridD.gridy = 4;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Ruta: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(rutaM), gridD);

        informacion.revalidate();
        informacion.repaint();

        JPanel song = new JPanel(new GridBagLayout());
        GridBagConstraints gridS = new GridBagConstraints();
        song.setBackground(Color.LIGHT_GRAY);
        gridS.insets = new Insets(5, 5, 5, 5);
        gridD.gridy = 4;
        gridD.gridx = 0;

        JLabel espacio = new JLabel("\n\n");
        gridB.gridy = 0;
        gridB.gridx = 0;
        song.add(espacio, gridS);

        ImageIcon backIcon = new ImageIcon("src/imags/previous32.png");
        Image backImage = backIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton back = new JButton(new ImageIcon(backImage));
        gridB.gridy = 0;
        gridB.gridx = 1;
        song.add(back, gridS);

        playIcon = new ImageIcon(new ImageIcon("src/imags/play32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        pauseIcon = new ImageIcon(new ImageIcon("src/imags/pause32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton play = new JButton(playIcon);
        gridB.gridy = 0;
        gridB.gridx = 2;
        song.add(play, gridS);

        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isPlaying) {
                    play.setIcon(playIcon);
                    rep.detener();
                    isPlaying = false;
                } else {
                    play.setIcon(pauseIcon);
                    rep.reproducir(rutaM);
                    isPlaying = true;
                }
            }
        });

        ImageIcon nextIcon = new ImageIcon("src/imags/next32.png");
        Image nextImage = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton next = new JButton(new ImageIcon(nextImage));
        gridB.gridy = 0;
        gridB.gridx = 3;
        song.add(next, gridS);

        isFav = user.verificarCancion(tituloM);
        nofavIcon = new ImageIcon(new ImageIcon("src/imags/heart32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        favIcon = new ImageIcon(new ImageIcon("src/imags/heartr32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton fav = new JButton(isFav ? favIcon : nofavIcon);
        gridB.gridy = 0;
        gridB.gridx = 4;
        song.add(fav, gridS);

        fav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isFav) {
                    fav.setIcon(favIcon);
                    user.agregarCancion(new File(rutaM));
                    isFav = true;
                } else {
                    fav.setIcon(nofavIcon);
                    user.eliminarCancion(tituloM);
                    isFav = false;
                }
                
                //fav.setIcon(user.verificarCancion(tituloM) ? favIcon : nofavIcon);
                refrescarListaMusica(musica, grid1, 1);

            }
        });

        informacion.add(song, gridD);
    }

    private void addButtonToPanelDMusica(JPanel panel, GridBagConstraints constraints, int d) {
       
        int componentCount = panel.getComponentCount();
        int row = componentCount / d;
        int col = componentCount % d;

        for (int i = 0; i < user.getCanciones().size(); i++) {
            
            String cancion = user.getCanciones().get(i).getTitulo();
            String art = user.getCanciones().get(i).getArtista();
            
            JButton newButton = new JButton();
            newButton.setPreferredSize(new Dimension(100, 60));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(new Color(0, 0, 0, 0));

            JLabel cancionLabel = new JLabel(cancion);
            cancionLabel.setHorizontalAlignment(SwingConstants.LEFT);
            cancionLabel.setFont(new Font("Arial Bold", Font.PLAIN, 17));
            cancionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            cancionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel artistLabel = new JLabel(art);
            artistLabel.setHorizontalAlignment(SwingConstants.LEFT);
            artistLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            artistLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            artistLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            buttonPanel.add(cancionLabel);
            buttonPanel.add(artistLabel);

            newButton.setLayout(new BorderLayout());
            newButton.add(buttonPanel, BorderLayout.CENTER);

            int index = i;
            newButton.addActionListener(ev -> {
                indiceM = index;
                System.out.println("indice: " + index + ", Cancion: " + cancion);
                tituloM = user.getCanciones().get(index).getTitulo();
                albumM = user.getCanciones().get(index).getAlbum();
                artistaM = user.getCanciones().get(index).getArtista();
                duracionM = user.getCanciones().get(index).getDuracion();
                rutaM = user.getCanciones().get(index).getRuta();
                imagenM = user.getCanciones().get(index).getFoto();
                
                actualizarInformacionMusica();

                // Asegúrate de que el panel sea visible
                informacion.setVisible(true);

                // Revalida y repinta el contenedor principal para reflejar los cambios
                revalidate();
                repaint();
            });

            constraints.gridx = col;
            constraints.gridy = row;
            panel.add(newButton, constraints);

            componentCount++;
            row = componentCount / d;
            col = componentCount % d;
        }

    }

    //JUEGOSSSSSSSSSSSSSSSSSSSSSSSSSSS
    private void actualizarInformacionJuego() {
        // Limpiar el contenido del panel
      
        informacion.removeAll();

        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(330, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        // Agregar la imagen
        ImageIcon icon2 = new ImageIcon(imagenJ);
        Image img2 = icon2.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        gridF.gridy = 0;
        gridF.gridx = 0;
        informacion.add(imageLabel2, gridF);

        // Crear el panel de descripción
        JPanel descripcion = new JPanel();
        descripcion.setBackground(Color.LIGHT_GRAY);
        descripcion.setLayout(new GridBagLayout());
        gridF.gridy = 1;
        gridF.gridx = 0;
        gridF.insets = new Insets(5, 5, 5, 5);
        informacion.add(descripcion, gridF);

        GridBagConstraints gridD = new GridBagConstraints();
        gridD.insets = new Insets(10, 10, 10, 10);

        // Primera fila
        gridD.gridy = 0;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Nombre:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(nombreJ), gridD);

        // Segunda fila
        gridD.gridy = 1;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Genero:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(generoJ), gridD);

        //tercera fila
        gridD.gridy = 2;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Desarrollador:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(desarrolladorJ), gridD);

        //cuarta fila
        gridD.gridy = 3;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Lanzamiento:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(lanzamientoJ), gridD);
        //quinta fila

        gridD.gridy = 4;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Ruta:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(rutaJ), gridD);

        // Actualizar visualización del panel
        informacion.revalidate();
        informacion.repaint();

        JPanel game = new JPanel(new GridBagLayout());
        GridBagConstraints gridS = new GridBagConstraints();
        game.setBackground(Color.LIGHT_GRAY);
        gridS.insets = new Insets(5, 5, 5, 5);
        gridD.gridy = 5;
        gridD.gridx = 0;

        noDown = new ImageIcon(new ImageIcon("src/imags/nodown32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        down = new ImageIcon(new ImageIcon("src/imags/down32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton bnAdd = new JButton(user.verificarJuego(nombreJ) ? down : noDown);
        gridB.gridy = 0;
        gridB.gridx = 1;
        game.add(bnAdd, gridS);

        bnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!user.verificarJuego(nombreJ)) {
                    bnAdd.setIcon(down);
                    user.agregarJuego(new File(rutaJ));

                } else {
                    bnAdd.setIcon(noDown);
                    user.eliminarJuego(nombreJ);
                }

                bnAdd.setIcon(user.verificarJuego(nombreJ) ? down : noDown);
                refrescarListaJuegos(juegos, grid2, 4); 
            }
        });

        informacion.add(game, gridD);

    }

    private void addButtonToPanelDJuegos(JPanel panel, GridBagConstraints constraints, int d) {
       
        int componentCount = panel.getComponentCount();
        int row = componentCount / d;
        int col = componentCount % d;

        for (int i = 0; i < user.getJuegos().size(); i++) {
            String juego = user.getJuegos().get(i).getNombre();
            String foto = user.getJuegos().get(i).getFoto();

            JButton newButton = new JButton();
            newButton.setPreferredSize(new Dimension(150, 250));
            newButton.setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(new Color(0, 0, 0, 0));

            ImageIcon icon2 = new ImageIcon(foto);
            Image img2 = icon2.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(img2);
            JLabel imageLabel2 = new JLabel(resizedIcon2);
            imageLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel juegoLabel = new JLabel(juego);
            juegoLabel.setHorizontalAlignment(SwingConstants.LEFT);
            juegoLabel.setFont(new Font("Arial Bold", Font.PLAIN, 17));
            juegoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            juegoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            buttonPanel.add(imageLabel2);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            buttonPanel.add(juegoLabel);

            newButton.add(buttonPanel, BorderLayout.CENTER);

            int index = i;
            newButton.addActionListener(ev -> {
                indiceJ = index;
                System.out.println("Índice: " + index + ", Juego: " + juego + " Foto " + foto);
                nombreJ = user.getJuegos().get(index).getNombre();
                generoJ = user.getJuegos().get(index).getGenero();
                desarrolladorJ = user.getJuegos().get(index).getDesarrollador();
                lanzamientoJ = user.getJuegos().get(index).getLanzamiento();
                rutaJ = user.getJuegos().get(index).getRuta();
                imagenJ = user.getJuegos().get(index).getFoto();
                
                actualizarInformacionJuego();

                informacion.setVisible(true);

                revalidate();
                repaint();
            });

            constraints.gridx = col;
            constraints.gridy = row;
            panel.add(newButton, constraints);

            componentCount++;
            row = componentCount / d;
            col = componentCount % d;
        }

    }

    private void refrescarListaJuegos(JPanel panel, GridBagConstraints constraints, int columnas) {
        panel.removeAll();

        addButtonToPanelDJuegos(panel, constraints, columnas);

        panel.revalidate();
        panel.repaint();
    }
    
    private void refrescarListaMusica(JPanel panel, GridBagConstraints constraints, int columnas) {
        panel.removeAll();

        addButtonToPanelDMusica(panel, constraints, columnas);

        panel.revalidate();
        panel.repaint();
    }


    private void mostrarInformacion(boolean esMusica) {
        if (esMusica) {
            actualizarInformacionMusica();
        } else {
            actualizarInformacionJuego();
        }

        if (!informacion.isVisible()) {
            add(informacion, gridInfo);
            informacion.setVisible(true);
        }

        revalidate();
        repaint();
    }

    public void mostrar() {
        mostrarInformacion(true);
    }

    public void mostrarJuego() {
        mostrarInformacion(false);
    }
    
     public void actualizarContenido() {
        user.actualizarJuegos();
        refrescarListaJuegos(juegos, grid2, 4);
        
        user.actualizarMusica();
        refrescarListaMusica(musica, grid1, 1);
        
    }

    
}
