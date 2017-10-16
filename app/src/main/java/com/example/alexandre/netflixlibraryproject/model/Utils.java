package com.example.alexandre.netflixlibraryproject.model;

/**
 * Created by Alexandre on 04-10-17.
 */

public abstract class Utils {

    public static class Intent{
        public static final String TAG_FILM = "FILM";
        public static final String TAG_APIKEY= "d3f617c2a1b78f7220853c4627424fe5";
        public static final String TAG_TITRE = "{\"unit\":6226,\"show_id\":70299043,\"show_title\":\"Attack on Titan\",\"release_year\":\"2013\",\"rating\":\"4.6\",\"category\":\"Anime\",\"show_cast\":\"Yuki Kaji, Yui Ishikawa, Marina Inoue, Daisuke Ono, Hiro Shimono, Hiroshi Kamiya, Keiji Fujiwara, Kish\\u00f4 Taniyama, Romi Park, Ryota Ohsaka\",\"director\":\"\",\"summary\":\"For over a century, people have been living behind barricades to block out the giant Titans that threaten to destroy the human race. When a Titan destroys his hometown, young Eren Yeager becomes determined to fight back.\",\"poster\":\"http://www.rmjv.net/images_1/FIGFP3463/g/FIGFP3463_1.jpg\",\"mediatype\":1}";
        public static final String TAG_ACTEUR = "[\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 636,\n" +
                "\n" +
                "      \"show_id\": 643557,\n" +
                "\n" +
                "      \"show_title\": \"It Could Happen to You\",\n" +
                "\n" +
                "      \"release_year\": \"1994\",\n" +
                "\n" +
                "      \"rating\": \"3.4\",\n" +
                "\n" +
                "      \"category\": \"Comedies\",\n" +
                "\n" +
                "      \"show_cast\": \"Nicolas Cage, Bridget Fonda, Rosie Pérez, Wendell Pierce, Isaac Hayes, Víctor Rojas, Seymour Cassel, Stanley Tucci, J.E. Freeman\",\n" +
                "\n" +
                "      \"director\": \"Andrew Bergman\",\n" +
                "\n" +
                "      \"summary\": \"In this charming romantic comedy based on a true story, a coffee-shop waitress gets a life-changing tip when a beat cop comes up short on pocket change and promises her half of his potential winnings from a lottery ticket.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/643557.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"101 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 800,\n" +
                "\n" +
                "      \"show_id\": 70143241,\n" +
                "\n" +
                "      \"show_title\": \"The Croods\",\n" +
                "\n" +
                "      \"release_year\": \"2013\",\n" +
                "\n" +
                "      \"rating\": \"4.1\",\n" +
                "\n" +
                "      \"category\": \"Children & Family Movies\",\n" +
                "\n" +
                "      \"show_cast\": \"Nicolas Cage, Emma Stone, Ryan Reynolds, Catherine Keener, Cloris Leachman, Clark Duke, Chris Sanders, Randy Thom\",\n" +
                "\n" +
                "      \"director\": \"Kirk De Micco, Chris Sanders\",\n" +
                "\n" +
                "      \"summary\": \"When an earthquake obliterates their cave, an unworldly prehistoric family is forced to journey through unfamiliar terrain in search of a new home. But things for pessimistic dad Grug go from bad to worse when his daughter meets a clever cave boy.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/70143241.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"98 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 2651,\n" +
                "\n" +
                "      \"show_id\": 70131173,\n" +
                "\n" +
                "      \"show_title\": \"Christmas Carol: The Movie\",\n" +
                "\n" +
                "      \"release_year\": \"2001\",\n" +
                "\n" +
                "      \"rating\": \"3.5\",\n" +
                "\n" +
                "      \"category\": \"Children & Family Movies\",\n" +
                "\n" +
                "      \"show_cast\": \"Simon Callow, Rhys Ifans, Kate Winslet, Nicolas Cage, Jane Horrocks, Michael Gambon, Juliet Stevenson, Robert Llewellyn, Iain Jones, Colin McFarlane\",\n" +
                "\n" +
                "      \"director\": \"Jimmy T. Murakami\",\n" +
                "\n" +
                "      \"summary\": \"Nicolas Cage, Kate Winslet and Simon Callow provide voices for this animated version of the classic Charles Dickens tale about the miserable Ebenezer Scrooge, who learns the true meaning of Christmas from three ghosts who confront him in the night. The film uses live-action sequences to bookend the story, and a mouse in the animated portion helps younger viewers follow the story. Winslet sings the film's main theme.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/70131173.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"77 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 3863,\n" +
                "\n" +
                "      \"show_id\": 70257818,\n" +
                "\n" +
                "      \"show_title\": \"Stolen\",\n" +
                "\n" +
                "      \"release_year\": \"2012\",\n" +
                "\n" +
                "      \"rating\": \"3.7\",\n" +
                "\n" +
                "      \"category\": \"Action & Adventure\",\n" +
                "\n" +
                "      \"show_cast\": \"Nicolas Cage, Josh Lucas, Danny Huston, Malin Akerman, Sami Gayle, Edrick Browne, Mark Valley, Barry Shabaka Henley, M.C. Gainey\",\n" +
                "\n" +
                "      \"director\": \"Simon West\",\n" +
                "\n" +
                "      \"summary\": \"Master thief Will Montgomery is ready to leave his criminal past behind. But when his daughter is kidnapped, he has no choice but to reunite with his old partner in crime and pull off one last heist.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/70257818.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"96 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 5221,\n" +
                "\n" +
                "      \"show_id\": 70135880,\n" +
                "\n" +
                "      \"show_title\": \"Seeking Justice\",\n" +
                "\n" +
                "      \"release_year\": \"2011\",\n" +
                "\n" +
                "      \"rating\": \"3.8\",\n" +
                "\n" +
                "      \"category\": \"Action & Adventure\",\n" +
                "\n" +
                "      \"show_cast\": \"Nicolas Cage, January Jones, Guy Pearce, Harold Perrineau, Jennifer Carpenter, Xander Berkeley, IronE Singleton, Wayne Pére, Marcus Lyle Brown, Jason Davis\",\n" +
                "\n" +
                "      \"director\": \"Roger Donaldson\",\n" +
                "\n" +
                "      \"summary\": \"After his wife is brutally raped, English teacher Will Gerard is approached by a man from a vigilante group who offers to exact revenge on the perpetrator. But once the deed is done, Will discovers that the group expects a certain favor in return.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/70135880.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"105 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 6000,\n" +
                "\n" +
                "      \"show_id\": 70206627,\n" +
                "\n" +
                "      \"show_title\": \"Trespass\",\n" +
                "\n" +
                "      \"release_year\": \"2011\",\n" +
                "\n" +
                "      \"rating\": \"3.5\",\n" +
                "\n" +
                "      \"category\": \"Thrillers\",\n" +
                "\n" +
                "      \"show_cast\": \"Nicolas Cage, Nicole Kidman, Ben Mendelsohn, Liana Liberato, Cam Gigandet, Jordana Spiro, Dash Mihok, Emily Meade, Nico Tortorella, Brandon Belknap\",\n" +
                "\n" +
                "      \"director\": \"Joel Schumacher\",\n" +
                "\n" +
                "      \"summary\": \"A husband and wife find themselves pushed to their absolute limit when they're held for ransom by brutal thugs who invade their home. As tensions escalate and shocking revelations emerge, the couple is forced to take ever-more desperate measures.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/70206627.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"91 min\"\n" +
                "\n" +
                "   }\n" +
                "\n" +
                "]";
        public static final String TAG_REALISATEUR = "[\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 84,\n" +
                "\n" +
                "      \"show_id\": 60032563,\n" +
                "\n" +
                "      \"show_title\": \"Kill Bill: Vol. 2\",\n" +
                "\n" +
                "      \"release_year\": \"2004\",\n" +
                "\n" +
                "      \"rating\": \"3.8\",\n" +
                "\n" +
                "      \"category\": \"Action & Adventure\",\n" +
                "\n" +
                "      \"show_cast\": \"Uma Thurman, David Carradine, Michael Madsen, Daryl Hannah, Gordon Liu, Michael Parks, Perla Haney-Jardine, Helen Kim, Claire Smithies, Clark Middleton\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino\",\n" +
                "\n" +
                "      \"summary\": \"The Bride has three left on her rampage list: Budd, Elle Driver and Bill himself. But when she arrives at Bill's house, she's in for a surprise.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/60032563.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"137 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 87,\n" +
                "\n" +
                "      \"show_id\": 60031236,\n" +
                "\n" +
                "      \"show_title\": \"Kill Bill: Vol. 1\",\n" +
                "\n" +
                "      \"release_year\": \"2003\",\n" +
                "\n" +
                "      \"rating\": \"3.8\",\n" +
                "\n" +
                "      \"category\": \"Action & Adventure\",\n" +
                "\n" +
                "      \"show_cast\": \"Uma Thurman, Lucy Liu, Vivica A. Fox, Daryl Hannah, David Carradine, Michael Madsen, Julie Dreyfus, Chiaki Kuriyama, Sonny Chiba, Gordon Liu\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino\",\n" +
                "\n" +
                "      \"summary\": \"An assassin is shot by her ruthless employer, Bill, and other members of their assassination circle. But she lives -- and plots her vengeance.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/60031236.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"111 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 914,\n" +
                "\n" +
                "      \"show_id\": 880640,\n" +
                "\n" +
                "      \"show_title\": \"Pulp Fiction\",\n" +
                "\n" +
                "      \"release_year\": \"1994\",\n" +
                "\n" +
                "      \"rating\": \"4.1\",\n" +
                "\n" +
                "      \"category\": \"Oscar-winning Movies\",\n" +
                "\n" +
                "      \"show_cast\": \"John Travolta, Samuel L. Jackson, Uma Thurman, Bruce Willis, Harvey Keitel, Tim Roth, Amanda Plummer, Ving Rhames, Eric Stoltz, Maria de Medeiros\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino\",\n" +
                "\n" +
                "      \"summary\": \"Weaving together three stories featuring a burger-loving hit man, his philosophical partner and a washed-up boxer, Quentin Tarantino influenced a generation of filmmakers with this crime caper's stylized, over-the-top violence and dark comic spirit.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/880640.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"154 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 943,\n" +
                "\n" +
                "      \"show_id\": 60010514,\n" +
                "\n" +
                "      \"show_title\": \"Jackie Brown\",\n" +
                "\n" +
                "      \"release_year\": \"1997\",\n" +
                "\n" +
                "      \"rating\": \"3.7\",\n" +
                "\n" +
                "      \"category\": \"Dramas\",\n" +
                "\n" +
                "      \"show_cast\": \"Pam Grier, Samuel L. Jackson, Robert Forster, Bridget Fonda, Michael Keaton, Robert De Niro, Michael Bowen, Chris Tucker, Lisa Gay Hamilton, Tommy 'Tiny' Lister\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino\",\n" +
                "\n" +
                "      \"summary\": \"Jackie Brown is an aging flight attendant who smuggles cash on the side. But when she's busted and pressured to help with an investigation, she plans to play the opposing forces against each other and walk away with the dough.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/60010514.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"154 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 1151,\n" +
                "\n" +
                "      \"show_id\": 902003,\n" +
                "\n" +
                "      \"show_title\": \"Reservoir Dogs\",\n" +
                "\n" +
                "      \"release_year\": \"1992\",\n" +
                "\n" +
                "      \"rating\": \"4.0\",\n" +
                "\n" +
                "      \"category\": \"Independent Movies\",\n" +
                "\n" +
                "      \"show_cast\": \"Harvey Keitel, Tim Roth, Michael Madsen, Steve Buscemi, Chris Penn, Lawrence Tierney, Edward Bunker, Quentin Tarantino, Randy Brooks, Kirk Baltz\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino\",\n" +
                "\n" +
                "      \"summary\": \"Quentin Tarantino's directorial debut is raw, violent, often mimicked -- and unforgettable. A botched robbery indicates a police informant, and the pressure mounts in the aftermath at a warehouse. Crime begets violence as the survivors unravel.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/902003.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"99 min\"\n" +
                "\n" +
                "   },\n" +
                "\n" +
                "   {\n" +
                "\n" +
                "      \"unit\": 1463,\n" +
                "\n" +
                "      \"show_id\": 520179,\n" +
                "\n" +
                "      \"show_title\": \"Four Rooms\",\n" +
                "\n" +
                "      \"release_year\": \"1995\",\n" +
                "\n" +
                "      \"rating\": \"3.6\",\n" +
                "\n" +
                "      \"category\": \"Comedies\",\n" +
                "\n" +
                "      \"show_cast\": \"Tim Roth, Antonio Banderas, Jennifer Beals, Bruce Willis, Paul Calderon, Madonna, Marisa Tomei, Quentin Tarantino, Ione Skye, Lili Taylor\",\n" +
                "\n" +
                "      \"director\": \"Quentin Tarantino, Robert Rodriguez, Allison Anders, Alexandre Rockwell\",\n" +
                "\n" +
                "      \"summary\": \"One mad New Year's Eve, an overwhelmed bellboy copes with witches and diabolical children, gets caught in the middle of a sour relationship and settles a bloody bet for members of a superstar's entourage.\",\n" +
                "\n" +
                "      \"poster\": \"http://netflixroulette.net/api/posters/520179.jpg\",\n" +
                "\n" +
                "      \"mediatype\": 0,\n" +
                "\n" +
                "      \"runtime\": \"98 min\"\n" +
                "\n" +
                "   }\n" +
                "\n" +
                "]";
    }
}
