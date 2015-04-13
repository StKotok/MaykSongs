package com.stkotok.mayksongs;

import java.util.ArrayList;
import java.util.List;

public class SongsService {
    public static final int NUMBER_OF_SONGS = 460;

    private static List<Song> songs;
    private static int[] numbers = new int[NUMBER_OF_SONGS];;

    public static List<Song> getSongs() {
        if (songs == null) {
            songs = new ArrayList<>(NUMBER_OF_SONGS);
            for (int i = 0; i < NUMBER_OF_SONGS;) {
                numbers[i] = ++i;
                Song song = createSong(songTextSTUB(), i);
                songs.add(song);
            }
        }
        return songs;
    }

    public static int[] getNumbers() {
        if (numbers == null) {
            getSongs();
        }
        return numbers;
    }

    private static Song createSong(String songString, int songNumber) {
        Song song = new Song();
        song.setText(songString);
        song.setNumber(songNumber);
        return song;
    }

    private static String songTextSTUB() {
        String text =
                " Em                                      H\n" +
                        "\n" +
                        "Ангелы в небе Господа славят,\n" +
                        "\n" +
                        "                   Am    H               Em                E\n" +
                        "\n" +
                        "Славу достойную Богу поют, Богу поют:\n" +
                        "\n" +
                        "               Am D                 G  C\n" +
                        "\n" +
                        "Вечная слава, вечная слава,\n" +
                        "\n" +
                        "              Am H                    Em                            E\n" +
                        "\n" +
                        "Вечная слава, слава Христу!    Слава Христу!\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Хочется с ангельским пением слиться\n" +
                        "\n" +
                        "В хоре искупленных, спасенных Господом.\n" +
                        "\n" +
                        "И сердце Господу, Богу единому,\n" +
                        "\n" +
                        "Христу Спасителю славу поет, славу поет.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "               Am D                 G  C\n" +
                        "\n" +
                        "Вечная слава, вечная слава,\n" +
                        "\n" +
                        "              Am H                    Em                          E\n" +
                        "\n" +
                        "Вечная слава, слава Христу! Слава Христу!\n" +
                        "\n" +
                        "               Am D                 G  C\n" +
                        "\n" +
                        "Вечная слава, вечная слава,\n" +
                        "\n" +
                        "              Am H                    Em            \n" +
                        "\n" +
                        "Вечная слава, слава Христу!\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Здесь, на земле людей, Церковь томится,\n" +
                        "\n" +
                        "К небу стремится, дух к Жениху влечёт.\n" +
                        "\n" +
                        "О, гряди, Господи, видишь, невеста ждёт,\n" +
                        "\n" +
                        "Тебе Единому славу поёт, славу поет.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Чудный, прославленный, царственный Божий Сын,\n" +
                        "\n" +
                        "Дивный Христос Господь в небе нас ждёт.\n" +
                        "\n" +
                        "И сердце Господу, Богу Единому,\n" +
                        "\n" +
                        "Христу Спасителю славу поёт, славу поет.";
        return text;
    }
}
