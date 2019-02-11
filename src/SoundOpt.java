public class SoundOpt {
    public static void avgEach(String picture) {
        System.out.println("pix");
        Picture pix = new Picture(picture);

        System.out.println("pix");
        Picture smallP = pix.scale(.5, .5);
        System.out.println("pix");

        smallP.write("normal");
        System.out.println("pix");

        smallP.explore();
    }

    public static void main(String[] args) {
        avgEach("Tulips.jpg");
    }
}
