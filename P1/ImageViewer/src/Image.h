/* 
 * The Image class represents image metadata -- filename and caption.
 * Author: Dave
 */

#ifndef IMAGE_H
#define	IMAGE_H

#include <string>

class Image 
{
    public:
        // Constructor
        Image(std::string newFilename, std::string newCaption);
        
        // Accessors
        std::string getFilename() const;
        std::string getCaption() const;
        
    private:
        // Fields
        std::string filename;
        std::string caption;
};

#endif

