import sys
from PIL import Image, ImageDraw, ImageFont
'''
    Auth: Xiaowu Chen
    Note: Please install [pillow] library before run this script.
'''
 
 
def draw_image(new_img, text, show_image=False):
    text = str(text)
    draw = ImageDraw.Draw(new_img)
    img_size = new_img.size
 
    font_size = 40
    fnt = ImageFont.truetype('xiao.ttf', font_size)
    fnt_size = fnt.getsize(text)
    while fnt_size[0] > img_size[0] or fnt_size[0] > img_size[0]:
        font_size -= 5
        fnt = ImageFont.truetype('xiao.ttf', font_size)
        fnt_size = fnt.getsize(text)
 
    x = (img_size[0] - fnt_size[0]) / 2
    y = (img_size[1] - fnt_size[1]) / 2
    draw.text((x, y), text, font=fnt, fill=(0, 0, 0))
 
    if show_image:
        new_img.show()
    del draw
 
 
def new_image(width, height, text='default', color=(255, 255, 255, 255), show_image=False):
    new_img = Image.new('RGBA', (int(width), int(height)), color)
    draw_image(new_img, text, show_image)
    new_img.save(r'E://images/%s.png' %  text)
    del new_img
 
 
def new_image_with_file(fn):
    with open(fn, encoding='utf-8') as f:
        for l in f:
            l = l.strip()
            if l:
                ls = l.split(',')
                if '#' == l[0] or len(ls) < 2:
                    continue
 
                new_image(*ls)
 
 
if '__main__' == __name__:
    name = sys.argv[1]
    new_image(400, 300, name, show_image=False)
    # new_image_with_file('image_data.txt')