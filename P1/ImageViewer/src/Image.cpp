#include "Image.h"

// Fairly trivial constructor.
Image::Image(std::string newFilename, std::string newCaption) 
:   filename(newFilename), 
    caption(newCaption)
{
}

// Accessors
std::string Image::getFilename() const
{
    return filename;
}

std::string Image::getCaption() const
{
    return caption;
}
