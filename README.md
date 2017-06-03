# JRPGEngine
A free-to-use JRPG engine developed as a hobby, let me know if you make something with it please :D

Features:
- Tiled TMX map support (XML)
- WASD Movement (configurable hotkeys soon to come)
- Background music, sound effect
- Menus (customisable with CSS)
- Click to interact (different interactions for near and far)
- Collision checking (with Tiled custom bool property COLLIDABLE)
- NPC and Cutscene support

There's only a bare minimum of code so that it can be used as a template for other games. There's not much functionality added and can be converted into a Platformer or Hack n Slash quite comfortably.
No documentation available as of yet but I'll try to respond if you email me: william.i.dcastro@gmail.com

GLHF

# How this works
In this engine, there are several "Screen" components that are displayed on the "GameStage". 

This is similar to a real life play or theatre where there is one stage but several scenes/stages (Screen objects) + several actors and props (other objects that extend the Drawable class). All these objects are added to the List "drawables" within each Screen object and the drawables are automatically drawn by the DrawingThread. The GameLogic component determines which Screen object to load for the chapter number. The chapter number is defined by the external flags file.

# Credits for free files
Images - all drawn by me

Sound files:
- Kwartet Japonski - Maciej Zolnowski
- Field Force - sawsquarenoise
- Game Sound Correct - bertrof
