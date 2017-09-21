/* 
 * Author: Dave
 */

#ifndef AMAZINGWINDOW_H
#define	AMAZINGWINDOW_H

#include <memory>
#include <gtkmm.h>

#include "Album.h"

/**
 * Represents our window, inheriting from Gtk::Window.
 */
class MainWindow : public Gtk::Window
{
    public:
        MainWindow(Album* newAlbum);  // Constructor
        ~MainWindow();                // Destructor
        
        // Accessor for the album (not really needed here, but just for illustration).
        Album* getAlbum() const;
        
    private:
        // Called to handle button presses
        void nextBtnHandler();
        void prevBtnHandler();
        
        // Album field, containing image information.
        Album* album;
        
        // Fields for window structure. A "VBox" is a container of widgets, which arranges its contents 
        // vertically. An "HButtonBox" arranges a set of buttons horizontally.
        Gtk::VBox vbox; 
        Gtk::HButtonBox toolbar;
        
        // Fields for GUI widgets. The two Gtk::Button widgets provide the "previous" and "next" 
        // controls. Gtk::Image displays the image. Gtk::Label shows the image caption.
        Gtk::Button nextBtn;
        Gtk::Button prevBtn;
        Gtk::Image imageWidget;
        Gtk::Label captionWidget;
};

#endif
