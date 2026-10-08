package com.example.data.local

import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity

object PreloadedCurriculum {

    fun getDefaultCategories(): List<CategoryEntity> = listOf(
        CategoryEntity(
            id = "sarali",
            name = "Sarali Varisais",
            description = "14 Fundamental exercises in Mayamalavagowla (Adi Tala) • carnatic.askharekrishna.com",
            iconKey = "stairs",
            orderIndex = 1
        ),
        CategoryEntity(
            id = "jantai",
            name = "Jantai Varisais",
            description = "14 Twin-swara exercises developing Sphurita gamaka & vocal firmness • carnatic.askharekrishna.com",
            iconKey = "double_arrow",
            orderIndex = 2
        ),
        CategoryEntity(
            id = "dattu",
            name = "Dattu Varisaigal",
            description = "6 Zigzag & skipping exercises training pitch precision across leaps • carnatic.askharekrishna.com",
            iconKey = "shuffle",
            orderIndex = 3
        ),
        CategoryEntity(
            id = "mel_sthayi",
            name = "Mel Sthayi Varisaigal",
            description = "10 Upper Sthayi exercises reaching Ṙ, Ġ, Ṁ, Ṗ • carnatic.askharekrishna.com",
            iconKey = "trending_up",
            orderIndex = 4
        ),
        CategoryEntity(
            id = "keel_sthayi",
            name = "Keel Sthayi Varisais",
            description = "Lower octave (Mandra Sthayi) resonance and depth exercises",
            iconKey = "trending_down",
            orderIndex = 5
        ),
        CategoryEntity(
            id = "alankaram",
            name = "Alankarams (Sapta Tala)",
            description = "7 Sapta Tala exercises mastering Dhruva, Matya, Rupaka, Jhampa, Triputa, Ata & Eka Talas • carnatic.askharekrishna.com",
            iconKey = "timer",
            orderIndex = 6
        ),
        CategoryEntity(
            id = "gitam",
            name = "Gitams",
            description = "Melodic compositions combining Swara, Sahitya & meanings • Includes Tiger Varadachariar's Karunaik Kadalamudhe",
            iconKey = "music_note",
            orderIndex = 7
        ),
        CategoryEntity(
            id = "varnam",
            name = "Varnams",
            description = "Complex vocal masterworks detailing raga swarupas and gamakas",
            iconKey = "auto_awesome",
            orderIndex = 8
        ),
        CategoryEntity(
            id = "ragam",
            name = "Ragams",
            description = "Raga alapana exploration, arohana-avarohana and hallmark phrases",
            iconKey = "graphic_eq",
            orderIndex = 9
        ),
        CategoryEntity(
            id = "kritis",
            name = "Kritis & Songs",
            description = "Repertoire of devotional and classical Carnatic master compositions",
            iconKey = "library_music",
            orderIndex = 10
        ),
        CategoryEntity(
            id = "teacher",
            name = "Teacher's Recordings",
            description = "Recordings captured during classes with your guru or external lessons",
            iconKey = "school",
            orderIndex = 11
        ),
        CategoryEntity(
            id = "converted",
            name = "Imported / Converted MP3s",
            description = "Audio imported from device or converted from external references",
            iconKey = "audio_file",
            orderIndex = 12
        )
    )

    fun getDefaultLessons(): List<LessonEntity> = listOf(
        // --- Sarali Varisais (Raga Mayamalavagowla, Tala Adi) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "sarali_1",
            categoryId = "sarali",
            title = "Sarali Varisai 1",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (1 Laghu of 4 + 2 Dhrutams of 2 = 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
• 1st Speed (1 note/beat - Prathama Kalam):
  S  | R  | G  | M  | P  | D  | N  | Ṡ  ||
  Ṡ  | N  | D  | P  | M  | G  | R  | S  ||
• 2nd Speed (2 notes/beat - Dvitiya Kalam):
  SR | GM | PD | NṠ || ṠN | DP | MG | RS ||
• 3rd Speed (4 notes/beat - Tritiya Kalam):
  SRGM | PDNṠ || ṠNDP | MGRS ||
• 4th Speed (8 notes/beat - Chaturtha Kalam):
  SRGMPDNṠ | ṠNDPMGRS ||
            """.trimIndent(),
            meaning = "Fundamental step-by-step swara sequence in Mayamalavagowla created by Purandara Dasa. Sing continuously through all 4 speeds maintaining pitch alignment.",
            youtubeUrl = "https://www.youtube.com/watch?v=-yUENqj56RY",
            orderIndex = 1
        ),
        LessonEntity(
            id = "sarali_2",
            categoryId = "sarali",
            title = "Sarali Varisai 2",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R S R | S R | G M ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N Ṡ N | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) maintaining steady tempo and clear swara sthanas.
            """.trimIndent(),
            meaning = "Focuses on stepping up to the second note and returning to base Shadjam (S R S R) and descending from Tara Shadjam (Ṡ N Ṡ N).",
            youtubeUrl = "https://www.youtube.com/watch?v=Kn4I6sejZZU",
            orderIndex = 2
        ),
        LessonEntity(
            id = "sarali_3",
            categoryId = "sarali",
            title = "Sarali Varisai 3",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G S | R G | S R ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D Ṡ | N D | Ṡ N ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Practice in 1st, 2nd, 3rd, and 4th speeds with smooth transitions across three swaras.
            """.trimIndent(),
            meaning = "3-note stepwise oscillation (S R G S | R G | S R) training breath and vocal agility.",
            youtubeUrl = "https://www.youtube.com/watch?v=8LCUjAjnJ8I",
            orderIndex = 3
        ),
        LessonEntity(
            id = "sarali_4",
            categoryId = "sarali",
            title = "Sarali Varisai 4",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | S R | G M ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds emphasizing equal duration for both repetitions of the first tetra-chord.
            """.trimIndent(),
            meaning = "Repeated Purvanga swaras (S R G M | S R | G M) strengthening rhythmic stability and lung stamina.",
            youtubeUrl = "https://www.youtube.com/watch?v=fSpMsInscyw",
            orderIndex = 4
        ),
        LessonEntity(
            id = "sarali_5",
            categoryId = "sarali",
            title = "Sarali Varisai 5",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P , | S R ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M , | Ṡ N ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Hold the Karvai note (P , in ascent and M , in descent) for exactly two aksharas across all speeds.
            """.trimIndent(),
            meaning = "Introduces Karvai (prolongation of 2 counts on Panchama P in ascent and Madhyama M in descent).",
            youtubeUrl = "https://www.youtube.com/watch?v=RoySw1F4h74",
            orderIndex = 5
        ),
        LessonEntity(
            id = "sarali_6",
            categoryId = "sarali",
            title = "Sarali Varisai 6",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P D | S R ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M G | Ṡ N ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Master the direct leap from D to base S, and M G to Tara Ṡ across all 4 speeds without pitch sliding.
            """.trimIndent(),
            meaning = "Practices interval leaps returning from Dhaivatha to Shadja (P D | S R) and Gandhara to Tara Shadja (M G | Ṡ N).",
            youtubeUrl = "https://www.youtube.com/watch?v=QUK9qE8EDaA",
            orderIndex = 6
        ),
        LessonEntity(
            id = "sarali_7",
            categoryId = "sarali",
            title = "Sarali Varisai 7",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P D | N , ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M G | R , ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Hold N , in arohanam and R , in avarohanam for 2 counts before proceeding.
            """.trimIndent(),
            meaning = "Karvai prolongation on Nishada (N ,) in ascent and Rishabha (R ,) in descent.",
            youtubeUrl = "https://www.youtube.com/watch?v=Uw71Rdz4cDU",
            orderIndex = 7
        ),
        LessonEntity(
            id = "sarali_8",
            categoryId = "sarali",
            title = "Sarali Varisai 8",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P D | N Ṡ ||
Ṡ , N D | P M | G R ||
S R G M | P D | N Ṡ ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds with a 2-count dwell on Tara Shadjam (Ṡ ,) at the beginning of the second line.
            """.trimIndent(),
            meaning = "Ascends to Tara Shadjam in the very first line, lingers with Karvai (Ṡ , N D) before stepping down and re-ascending.",
            youtubeUrl = "https://www.youtube.com/watch?v=jSaI_yOSMNI",
            orderIndex = 8
        ),
        LessonEntity(
            id = "sarali_9",
            categoryId = "sarali",
            title = "Sarali Varisai 9",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P M | D P ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M P | G M ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Focus on the zigzag oscillatory patterns (P M | D P) and (M P | G M) across all 4 speeds.
            """.trimIndent(),
            meaning = "Zigzag oscillation around Madhyama and Panchama (P M | D P and M P | G M) training quick vocal turns.",
            youtubeUrl = "https://www.youtube.com/watch?v=2Y_5kUJCpC4",
            orderIndex = 9
        ),
        LessonEntity(
            id = "sarali_10",
            categoryId = "sarali",
            title = "Sarali Varisai 10",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P , | G M ||
P , , , | P , | , , ||
G M P D | N D | P M ||
G M P , | G M | P , ||
S R G M | P D | N Ṡ ||
Avarohanam:
Ṡ N D P | M , | D P ||
M , , , | M , | , , ||
G M P D | N D | P M ||
G M P , | G M | P , ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Count the 4-akshara and 6-akshara Karvai extensions accurately across all 4 speeds with the tala.
            """.trimIndent(),
            meaning = "Extended multi-avartana exercise featuring deep karvais on Panchama (P , , , | P , | , ,) and Madhyama.",
            youtubeUrl = "https://www.youtube.com/watch?v=OCsNH4NgxOQ",
            orderIndex = 10
        ),
        LessonEntity(
            id = "sarali_11",
            categoryId = "sarali",
            title = "Sarali Varisai 11",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P D | N Ṡ ||
Ṡ N D P | Ṡ N | D P ||
S R G M | P D | N Ṡ ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds maintaining rhythmic symmetry across the repeated upper-octave descending patterns.
            """.trimIndent(),
            meaning = "Alternates full ascending scale with repeated upper-tetrarch descent (Ṡ N D P | Ṡ N | D P).",
            youtubeUrl = "https://www.youtube.com/watch?v=7-2Bb4-_LOc",
            orderIndex = 11
        ),
        LessonEntity(
            id = "sarali_12",
            categoryId = "sarali",
            title = "Sarali Varisai 12",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S S R R | G G | M M ||
P P D D | N N | Ṡ Ṡ ||
Avarohanam:
Ṡ Ṡ N N | D D | P P ||
M M G G | R R | S S ||

Singing Speed Guide (All 4 Speeds):
Articulate both notes clearly in each pair without slurring; sing in 1st, 2nd, 3rd, and 4th speeds.
            """.trimIndent(),
            meaning = "Twin-swara (doubled note) exercise serving as the foundational gateway to Jantai Varisais.",
            youtubeUrl = "https://www.youtube.com/watch?v=CYRad6LtKcA",
            orderIndex = 12
        ),
        LessonEntity(
            id = "sarali_13",
            categoryId = "sarali",
            title = "Sarali Varisai 13",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G R | G , | R G ||
M , , , | , , | , , ||
R G M G | M , | G M ||
P , , , | , , | , , ||
G M P M | P , | M P ||
D , , , | , , | , , ||
M P D P | D , | P D ||
N , , , | , , | , , ||
P D N D | N , | D N ||
Ṡ , , , | , , | , , ||
Avarohanam:
Ṡ N D N | D , | N D ||
P , , , | , , | , , ||
N D P D | P , | D P ||
M , , , | , , | , , ||
D P M P | M , | P M ||
G , , , | , , | , , ||
P M G M | G , | M G ||
R , , , | , , | , , ||
M G R G | R , | G R ||
S , , , | , , | , , ||

Singing Speed Guide (All 4 Speeds):
Master the step-ladder progression and the 6-count Karvai rests on each note in all 4 speeds.
            """.trimIndent(),
            meaning = "Step-ladder progression with full 6-count Karvai rests on M, P, D, N, Ṡ in ascent and P, M, G, R, S in descent.",
            youtubeUrl = "https://www.youtube.com/watch?v=qOgYfQTnKFY",
            orderIndex = 13
        ),
        LessonEntity(
            id = "sarali_14",
            categoryId = "sarali",
            title = "Sarali Varisai 14",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Arohanam:
S R G M | P , | P , ||
D D P , | M M | P , ||
D N Ṡ , | Ṡ N | D P ||
Avarohanam:
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Culmination exercise of Sarali series. Sing in 1st, 2nd, 3rd, and 4th speeds with confident tala synchronization.
            """.trimIndent(),
            meaning = "Grand finale of Sarali series combining karvais, paired swaras, interval leaps, and final descent.",
            youtubeUrl = "https://www.youtube.com/watch?v=K7v5II1wqlc",
            orderIndex = 14
        ),

        // --- Jantai Varisais (Raga Mayamalavagowla, Tala Adi) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "jantai_1",
            categoryId = "jantai",
            title = "Jantai Varisai 1",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RR GG MM | PP DD | NN ṠṠ ||
ṠṠ NN DD PP | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Fundamental paired-swara exercise. Pronounce the second swara in each pair with a forceful, pristine pulsation (Sphuritam) from the navel.",
            youtubeUrl = "https://www.youtube.com/watch?v=M_I81iF35NE",
            orderIndex = 1
        ),
        LessonEntity(
            id = "jantai_2",
            categoryId = "jantai",
            title = "Jantai Varisai 2",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RR GG MM | RR GG | MM PP ||
GG MM PP DD | MM PP | DD NN ||
PP DD NN ṠṠ | ṠṠ NN | DD PP ||
NN DD PP MM | DD PP | MM GG ||
PP MM GG RR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Stepping progression of twin swaras (SS RR GG MM | RR GG | MM PP) training continuous rhythmic displacement in pairs.",
            youtubeUrl = "https://www.youtube.com/watch?v=tFR3wVnR0xQ",
            orderIndex = 2
        ),
        LessonEntity(
            id = "jantai_3",
            categoryId = "jantai",
            title = "Jantai Varisai 3",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RR GG RR | SS RR | GG MM ||
RR GG MM GG | RR GG | MM PP ||
GG MM PP MM | GG MM | PP DD ||
MM PP DD PP | MM PP | DD NN ||
PP DD NN DD | PP DD | NN ṠṠ ||
ṠṠ NN DD NN | ṠṠ NN | DD PP ||
NN DD PP DD | NN DD | PP MM ||
DD PP MM PP | DD PP | MM GG ||
PP MM GG MM | PP MM | GG RR ||
MM GG RR GG | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Turnaround pattern stepping back to preceding swara (SS RR GG RR | SS RR | GG MM) developing fine pitch recoil.",
            youtubeUrl = "https://www.youtube.com/watch?v=tC7LBKDoXsc",
            orderIndex = 3
        ),
        LessonEntity(
            id = "jantai_4",
            categoryId = "jantai",
            title = "Jantai Varisai 4",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS ,-R R, GG | SS RR | GG MM ||
RR ,-G G, MM | RR GG | MM PP ||
GG ,-M M, PP | GG MM | PP DD ||
MM ,-P P, DD | MM PP | DD NN ||
PP ,-D D, NN | PP DD | NN ṠṠ ||
ṠṠ ,-N N, DD | ṠṠ NN | DD PP ||
NN ,-D D, PP | NN DD | PP MM ||
DD ,-P P, MM | DD PP | MM GG ||
PP ,-M M, GG | PP MM | GG RR ||
MM ,-G G, RR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Dhattu-accentuated pair with Karvai pause (SS ,-R R, GG), strengthening breath regulation and Sphurita micro-attack.",
            youtubeUrl = "https://www.youtube.com/watch?v=rpCgBZS0ZJg",
            orderIndex = 4
        ),
        LessonEntity(
            id = "jantai_5",
            categoryId = "jantai",
            title = "Jantai Varisai 5",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S, S-R ,R GG | SS RR | GG MM ||
R, R-G ,G MM | RR GG | MM PP ||
G, G-M ,M PP | GG MM | PP DD ||
M, M-P ,P DD | MM PP | DD NN ||
P, P-D ,D NN | PP DD | NN ṠṠ ||
Ṡ, Ṡ-N ,N DD | ṠṠ NN | DD PP ||
N, N-D ,D PP | NN DD | PP MM ||
D, D-P ,P MM | DD PP | MM GG ||
P, P-M ,M GG | PP MM | GG RR ||
M, M-G ,G RR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Prolonged first note followed by downward push (S, S-R ,R GG) cultivating deep diaphragm stability and pitch anchoring.",
            youtubeUrl = "https://www.youtube.com/watch?v=0aw3cKEVxdw",
            orderIndex = 5
        ),
        LessonEntity(
            id = "jantai_6",
            categoryId = "jantai",
            title = "Jantai Varisai 6",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS SR RR GG | SS RR | GG MM ||
RR RG GG MM | RR GG | MM PP ||
GG GM MM PP | GG MM | PP DD ||
MM MP PP DD | MM PP | DD NN ||
PP PD DD NN | PP DD | NN ṠṠ ||
ṠṠ ṠN NN DD | ṠṠ NN | DD PP ||
NN ND DD PP | NN DD | PP MM ||
DD DP PP MM | DD PP | MM GG ||
PP PM MM GG | PP MM | GG RR ||
MM MG GG RR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Triple-swara lead into twin swaras (SS SR RR GG) developing brisk agility and ornamentation readiness.",
            youtubeUrl = "https://www.youtube.com/watch?v=j7AYmJyZK9I",
            orderIndex = 6
        ),
        LessonEntity(
            id = "jantai_7",
            categoryId = "jantai",
            title = "Jantai Varisai 7",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RS SR SR | SS RR | GG MM ||
RR GR RG RG | RR GG | MM PP ||
GG MG GM GM | GG MM | PP DD ||
MM PM MP MP | MM PP | DD NN ||
PP DP PD PD | PP DD | NN ṠṠ ||
ṠṠ NṠ ṠN ṠN | ṠṠ NN | DD PP ||
NN DN ND ND | NN DD | PP MM ||
DD PD DP DP | DD PP | MM GG ||
PP MP PM PM | PP MM | GG RR ||
MM GM MG MG | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Rapid oscillatory turns (SS RS SR SR) before stepping forward, building vocal flexibility and tremolo resistance.",
            youtubeUrl = "https://www.youtube.com/watch?v=uEpW7a0WaWk",
            orderIndex = 7
        ),
        LessonEntity(
            id = "jantai_8",
            categoryId = "jantai",
            title = "Jantai Varisai 8",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RR GS RG | SS RR | GG MM ||
RR GG MR GM | RR GG | MM PP ||
GG MM PG MP | GG MM | PP DD ||
MM PP DM PD | MM PP | DD NN ||
PP DD NP DN | PP DD | NN ṠṠ ||
ṠṠ NN DS ND | ṠṠ NN | DD PP ||
NN DD PN DP | NN DD | PP MM ||
DD PP MD PM | DD PP | MM GG ||
PP MM GP MG | PP MM | GG RR ||
MM GG RM GR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Zigzag leap across swaras (SS RR GS RG) linking Janta power with Dhattu skipping agility.",
            youtubeUrl = "https://www.youtube.com/watch?v=HetnfxFCc-k",
            orderIndex = 8
        ),
        LessonEntity(
            id = "jantai_9",
            categoryId = "jantai",
            title = "Jantai Varisai 9",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS MM GG RR | SS RR | GG MM ||
RR PP MM GG | RR GG | MM PP ||
GG DD PP MM | GG MM | PP DD ||
MM NN DD PP | MM PP | DD NN ||
PP ṠṠ NN DD | PP DD | NN ṠṠ ||
ṠṠ PP DD NN | ṠṠ NN | DD PP ||
NN MM PP DD | NN DD | PP MM ||
DD GG MM PP | DD PP | MM GG ||
PP RR GG MM | PP MM | GG RR ||
MM SS RR GG | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Wide interval leaps from Shadjam to Madhyamam (SS MM GG RR) testing accurate vocal placement across wide leaps.",
            youtubeUrl = "https://www.youtube.com/watch?v=h6ECn-pBCFI",
            orderIndex = 9
        ),
        LessonEntity(
            id = "jantai_10",
            categoryId = "jantai",
            title = "Jantai Varisai 10",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS SS RR RR | GG GG | MM MM ||
PP PP DD DD | NN NN | ṠṠ ṠṠ ||
ṠṠ ṠṠ NN NN | DD DD | PP PP ||
MM MM GG GG | RR RR | SS SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Quadrupled swaras (4 pulses of each note: SS SS RR RR), forging immense vocal endurance and steady breath control.",
            youtubeUrl = "https://www.youtube.com/watch?v=ksJ9P5-bpic",
            orderIndex = 10
        ),
        LessonEntity(
            id = "jantai_11",
            categoryId = "jantai",
            title = "Jantai Varisai 11",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S, SS R, RR | G, GG | M, MM ||
P, PP D, DD | N, NN | Ṡ, ṠṠ ||
Ṡ, ṠṠ N, NN | D, DD | P, PP ||
M, MM G, GG | R, RR | S, SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Single Karvai note leading into pair (S, SS R, RR), establishing clean contrast between single and doubled notes.",
            youtubeUrl = "https://www.youtube.com/watch?v=D547RjA2Fgc",
            orderIndex = 11
        ),
        LessonEntity(
            id = "jantai_12",
            categoryId = "jantai",
            title = "Jantai Varisai 12",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS ,S RR ,R | GG ,G | MM ,M ||
PP ,P DD ,D | NN ,N | ṠṠ ,Ṡ ||
ṠṠ ,Ṡ NN ,N | DD ,D | PP ,P ||
MM ,M GG ,G | RR ,R | SS ,S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Pair with intermediate Karvai bounce (SS ,S RR ,R), training precise acoustic separation and vocal attack.",
            youtubeUrl = "https://www.youtube.com/watch?v=D547RjA2Fgc",
            orderIndex = 12
        ),
        LessonEntity(
            id = "jantai_13",
            categoryId = "jantai",
            title = "Jantai Varisai 13",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS S, RR R, | GG G, | MM M, ||
PP P, DD D, | NN N, | ṠṠ Ṡ, ||
ṠṠ Ṡ, NN N, | DD D, | PP P, ||
MM M, GG G, | RR R, | SS S, ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Pair with prolonged trailing note (SS S, RR R,), perfecting sustaining power after doubled pulsation.",
            youtubeUrl = "https://www.youtube.com/watch?v=D547RjA2Fgc",
            orderIndex = 13
        ),
        LessonEntity(
            id = "jantai_14",
            categoryId = "jantai",
            title = "Jantai Varisai 14",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
SS RS SR SR | GR G, | SR RG ||
SS RR GS RG | SS RR | GG MM ||
RR GR RG RG | MG M, | RG GM ||
RR GG MR GM | RR GG | MM PP ||
GG MG GM GM | PM P, | GM MP ||
GG MM PG MP | GG MM | PP DD ||
MM PM MP MP | DP D, | MP PD ||
MM PP DM PD | MM PP | DD NN ||
PP DP PD PD | ND N, | PD DN ||
PP DD NP DN | PP DD | NN ṠṠ ||
ṠṠ NṠ ṠN ṠN | DN D, | ṠN ND ||
ṠṠ NN DS ND | ṠṠ NN | DD PP ||
NN DN ND ND | PD P, | ND DP ||
NN DD PN DP | NN DD | PP MM ||
DD PD DP DP | MP M, | DP PM ||
DD PP MD PM | DD PP | MM GG ||
PP MP PM PM | GM G, | PM MG ||
PP MM GP MG | PP MM | GG RR ||
MM GM MG MG | RG R, | MG GR ||
MM GG RM GR | MM GG | RR SS ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with Sphurita gamaka accentuating the second note in each pair.
            """.trimIndent(),
            meaning = "Grand Jantai masterpiece combining oscillatory turns, Karvai accents, jumps, and progressions into a rich avartana.",
            youtubeUrl = "https://www.youtube.com/watch?v=D547RjA2Fgc",
            orderIndex = 14
        ),

        // --- Dattu Varisaigal (Raga Mayamalavagowla, Tala Adi) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "dattu_1",
            categoryId = "dattu",
            title = "Dattu Varisai 1",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S M G R | S R | G M ||
R P M G | R G | M P ||
G D P M | G M | P D ||
M N D P | M P | D N ||
P Ṡ N D | P D | N Ṡ ||
Ṡ P D N | Ṡ N | D P ||
N M P D | N D | P M ||
D G M P | D P | M G ||
P R G M | P M | G R ||
M S R G | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Alternate note leaps (S to M, R to P, G to D, M to N, P to Ṡ) in ascent and wide returns in descent. Sharpens swara sthana mental map and pitch agility.",
            youtubeUrl = "https://www.youtube.com/watch?v=0T8ZnvS8HTI",
            orderIndex = 1
        ),
        LessonEntity(
            id = "dattu_2",
            categoryId = "dattu",
            title = "Dattu Varisai 2",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S G R G | S R | G M ||
R M G M | R G | M P ||
G P M P | G M | P D ||
M D P D | M P | D N ||
P N D N | P D | N Ṡ ||
Ṡ D N D | Ṡ N | D P ||
N P D P | N D | P M ||
D M P M | D P | M G ||
P G M G | P M | G R ||
M R G R | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Skip-and-step pattern (S G R G | S R | G M) testing intermediate interval anchoring before ascending sequentially.",
            youtubeUrl = "https://www.youtube.com/watch?v=2WiWWrfWgZM",
            orderIndex = 2
        ),
        LessonEntity(
            id = "dattu_3",
            categoryId = "dattu",
            title = "Dattu Varisai 3",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S M G M | R G | S R ||
S G R G | S R | G M ||
R P M P | G M | R G ||
R M G M | R G | M P ||
G D P D | M P | G M ||
G P M P | G M | P D ||
M N D N | P D | M P ||
M D P D | M P | D N ||
P Ṡ N Ṡ | D N | P D ||
P N D N | P D | N Ṡ ||
Ṡ P D P | N D | Ṡ N ||
Ṡ D N D | Ṡ N | D P ||
N M P M | D P | N D ||
N P D P | N D | P M ||
D G M G | P M | D P ||
D M P M | D P | M G ||
P R G R | M G | P M ||
P G M G | P M | G R ||
M S R S | G R | M G ||
M R G R | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Complex turnarounds and 4th-interval leaps (S M G M | R G | S R) requiring steady breath control and rapid vocal adjustments.",
            youtubeUrl = "https://www.youtube.com/watch?v=Dll1Nma48Ng",
            orderIndex = 3
        ),
        LessonEntity(
            id = "dattu_4",
            categoryId = "dattu",
            title = "Dattu Varisai 4",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R S G | R G | R M ||
S R G R | G R | G M ||
R G R M | G M | G P ||
R G M G | M G | M P ||
G M G P | M P | M D ||
G M P M | P M | P D ||
M P M D | P D | P N ||
M P D P | D P | D N ||
P D P N | D N | D Ṡ ||
P D N D | N D | N Ṡ ||
Ṡ N Ṡ D | N D | N P ||
Ṡ N D N | D N | D P ||
N D N P | D P | D M ||
N D P D | P D | P M ||
D P D M | P M | P G ||
D P M P | M P | M G ||
P M P G | M G | M R ||
P M G M | G M | G R ||
M G M R | G R | G S ||
M G R G | R G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Oscillatory zigzag leaps (S R S G | R G | R M) developing throat flexibility and preventing pitch sliding.",
            youtubeUrl = "https://www.youtube.com/watch?v=gZrRLRHRy4g",
            orderIndex = 4
        ),
        LessonEntity(
            id = "dattu_5",
            categoryId = "dattu",
            title = "Dattu Varisai 5",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S M G M | R G | S R ||
S S R R | G G | M M ||
R P M P | G M | R G ||
R R G G | M M | P P ||
G D P D | M P | G M ||
G G M M | P P | D D ||
M N D N | P D | M P ||
M M P P | D D | N N ||
P Ṡ N Ṡ | D N | P D ||
P P D D | N N | Ṡ Ṡ ||
Ṡ P D P | N D | Ṡ N ||
Ṡ Ṡ N N | D D | P P ||
N M P M | D P | N D ||
N N D D | P P | M M ||
D G M G | P M | D P ||
D D P P | M M | G G ||
P R G R | M G | P M ||
P P M M | G G | R R ||
M S R S | G R | M G ||
M M G G | R R | S S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Dhattu leaps fused with Jantai twin swaras (S M G M | R G | S R followed by SS RR | GG | MM). Unites interval skipping with Sphurita impulse power.",
            youtubeUrl = "https://www.youtube.com/watch?v=HJ7iJ3gJ2WA",
            orderIndex = 5
        ),
        LessonEntity(
            id = "dattu_6",
            categoryId = "dattu",
            title = "Dattu Varisai 6",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S M G M | R G | S R ||
S M G R | S R | G M ||
R P M P | G M | R G ||
R P M G | R G | M P ||
G D P D | M P | G M ||
G D P M | G M | P D ||
M N D N | P D | M P ||
M N D P | M P | D N ||
P Ṡ N Ṡ | D N | P D ||
P Ṡ N D | P D | N Ṡ ||
Ṡ P D P | N D | Ṡ N ||
Ṡ P D N | Ṡ N | D P ||
N M P M | D P | N D ||
N M P D | N D | P M ||
D G M G | P M | D P ||
D G M P | D P | M G ||
P R G R | M G | P M ||
P R G M | P M | G R ||
M S R S | G R | M G ||
M S R G | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) ensuring accurate interval leaps without pitch slurs or glides.
            """.trimIndent(),
            meaning = "Grand synthesis of Dhattu Varisaigal integrating alternating leaps, turnaround patterns, and stepping cadences across the entire octave.",
            youtubeUrl = "https://www.youtube.com/watch?v=TN7vpD4rb0E",
            orderIndex = 6
        ),

        // --- Mel Sthayi Varisaigal (Upper Sthayi - Raga Mayamalavagowla, Tala Adi) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "mel_1",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 1",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
D N Ṡ Ṙ | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Reaches Tara Rishabham (Ṙ) after sustaining Tara Shadjam (Ṡ). Establishes proper vocal placement for the upper register without straining.",
            youtubeUrl = "https://www.youtube.com/watch?v=XvAhnDiOIV4",
            orderIndex = 1
        ),
        LessonEntity(
            id = "mel_2",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 2",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
D N Ṡ Ṙ | Ṡ Ṡ | Ṙ Ṡ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Repeated excursions to Tara Rishabham (Ṙ) alternating with Madhya octave Madhyama and Panchama anchor notes.",
            youtubeUrl = "https://www.youtube.com/watch?v=yR0OWNAGhC4",
            orderIndex = 2
        ),
        LessonEntity(
            id = "mel_3",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 3",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
D N Ṡ Ṙ | Ġ Ṙ | Ṡ Ṙ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ Ṡ | Ṙ Ṡ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Ascends to Tara Gandharam (Ġ). Requires relaxed throat and strong breath column to produce clear upper notes.",
            youtubeUrl = "https://www.youtube.com/watch?v=7MT4cbnKFds",
            orderIndex = 3
        ),
        LessonEntity(
            id = "mel_4",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 4",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
D N Ṡ Ṙ | Ġ Ṁ | Ġ Ṙ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ġ Ṙ | Ṡ Ṙ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ Ṡ | Ṙ Ṡ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Ascends to Tara Madhyamam (Ṁ), building vocal flexibility and breath stamina in the higher register.",
            youtubeUrl = "https://www.youtube.com/watch?v=5FE2VbcftVI",
            orderIndex = 4
        ),
        LessonEntity(
            id = "mel_5",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 5",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
D N Ṡ Ṙ | Ġ Ṁ | Ṗ Ṁ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ġ Ṁ | Ġ Ṙ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ġ Ṙ | Ṡ Ṙ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ Ṡ | Ṙ Ṡ ||
Ṡ Ṙ Ṡ N | D P | M P ||
D N Ṡ Ṙ | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "The apex range exercise of Carnatic foundation, reaching Tara Panchamam (Ṗ). Practice in a relaxed posture without pushing vocal volume.",
            youtubeUrl = "https://www.youtube.com/watch?v=GCCKQeeKBmg",
            orderIndex = 5
        ),
        LessonEntity(
            id = "mel_6",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 6",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
Ṡ Ṙ Ġ Ṙ | Ṡ Ṡ | N Ṡ ||
Ṙ Ṡ Ṡ N | D N | Ṡ N ||
N D P D | N D | P M ||
M P D P | M P | D N ||
P M D M | P P | D N ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Advanced downward melodic phrases originating from Tara Gandharam (Ṡ Ṙ Ġ Ṙ | Ṡ Ṡ | N Ṡ), developing agility and ornamentation readiness in high pitch.",
            youtubeUrl = "https://www.youtube.com/watch?v=mEthYd-CzxM",
            orderIndex = 6
        ),
        LessonEntity(
            id = "mel_7",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 7",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
Ġ Ṙ Ṙ Ṡ | Ṡ N | Ṙ Ṡ ||
Ṡ N N D | Ṡ N | P D ||
D P N D | D P | M P ||
D P P M | D M | D P ||
G M P D | Ṡ N | D Ṡ ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Tara Sthayi twin notes and leaps (Ġ Ṙ Ṙ Ṡ | Ṡ N | Ṙ Ṡ) forging firm vocal attack and steady intonation.",
            youtubeUrl = "https://www.youtube.com/watch?v=mEthYd-CzxM",
            orderIndex = 7
        ),
        LessonEntity(
            id = "mel_8",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 8",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
Ṡ Ṙ Ṡ Ṙ | Ṡ D | N N ||
N Ṡ N D | P D | N N ||
D P P P | P D | P M ||
M P M P | M G | M P ||
G M P D | Ṡ N | D P ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Tara register oscillations (Ṡ Ṙ Ṡ Ṙ | Ṡ D | N N) stabilizing vocal cords and preventing pitch tremors.",
            youtubeUrl = "https://www.youtube.com/watch?v=mEthYd-CzxM",
            orderIndex = 8
        ),
        LessonEntity(
            id = "mel_9",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 9",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
Ṡ Ṙ Ġ , | Ṙ Ṡ | N Ṡ ||
Ṙ Ṡ N D | D N | Ṡ P ||
N D P D | N P | G M ||
M P D G | M P | D N ||
P M D P | M P | G S ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Sustained Karvai dwell on Tara Gandharam (Ṡ Ṙ Ġ , | Ṙ Ṡ | N Ṡ) cultivating breath economy and sweet resonance in higher register.",
            youtubeUrl = "https://www.youtube.com/watch?v=mEthYd-CzxM",
            orderIndex = 9
        ),
        LessonEntity(
            id = "mel_10",
            categoryId = "mel_sthayi",
            title = "Mel Sthayi Varisai 10",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | P D | N Ṡ ||
Ṡ , , , | Ṡ , | , , ||
Ṡ , Ṙ Ġ | Ṙ Ṡ | N , ||
Ṡ Ṙ Ṡ N | D , | N Ṡ ||
N D P , | D N | D P ||
M , P D | P M | G , ||
M P M D | P M | D N ||
Ṡ N D P | M G | R S ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with an open throat, keeping the chest and neck relaxed as you ascend into the Tara Sthayi.
            """.trimIndent(),
            meaning = "Culmination of Upper Sthayi series featuring syncopated Karvais across both Tara and Madhya registers.",
            youtubeUrl = "https://www.youtube.com/watch?v=mEthYd-CzxM",
            orderIndex = 10
        ),

        // --- Keel Sthayi Varisaigal (Lower Sthayi - Raga Mayamalavagowla, Tala Adi) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "keel_1",
            categoryId = "keel_sthayi",
            title = "Keel Sthayi Varisai 1",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Ṡ N D P | M G | R S ||
S , , , | S , | , , ||
G R S Ṇ | S R | G M ||
S R G M | P D | N Ṡ ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with relaxed vocal chords, grounding the lower notes from the chest and lower abdomen.
            """.trimIndent(),
            meaning = "Initiates descent into Mandra Sthayi (lower register) reaching Mandra Nishadam (Ṇ) after sustaining Adhara Shadjam (S). Vital for chest resonance and vocal warmth.",
            youtubeUrl = "https://www.youtube.com/watch?v=OrxQgrAA5gw",
            orderIndex = 1
        ),
        LessonEntity(
            id = "keel_2",
            categoryId = "keel_sthayi",
            title = "Keel Sthayi Varisai 2",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Ṡ N D P | M G | R S ||
S , , , | S , | , , ||
G R S Ṇ | S S | Ṇ S ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S R | G M ||
S R G M | P D | N Ṡ ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with relaxed vocal chords, grounding the lower notes from the chest and lower abdomen.
            """.trimIndent(),
            meaning = "Repeated excursions into Mandra Nishadam (Ṇ) alternating with Madhya octave Madhyamam and Panchamam, strengthening lower pitch stability.",
            youtubeUrl = "https://www.youtube.com/watch?v=HGCdym90zjs",
            orderIndex = 2
        ),
        LessonEntity(
            id = "keel_3",
            categoryId = "keel_sthayi",
            title = "Keel Sthayi Varisai 3",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Ṡ N D P | M G | R S ||
S , , , | S , | , , ||
G R S Ṇ | Ḍ Ṇ | S Ṇ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S S | Ṇ S ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S R | G M ||
S R G M | P D | N Ṡ ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with relaxed vocal chords, grounding the lower notes from the chest and lower abdomen.
            """.trimIndent(),
            meaning = "Deepens lower range to Mandra Dhaivatam (Ḍ). Promotes deep abdominal breath support and eliminates throat constriction.",
            youtubeUrl = "https://www.youtube.com/watch?v=dtslbw_CzbA",
            orderIndex = 3
        ),
        LessonEntity(
            id = "keel_4",
            categoryId = "keel_sthayi",
            title = "Keel Sthayi Varisai 4",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Ṡ N D P | M G | R S ||
S , , , | S , | , , ||
G R S Ṇ | Ḍ P̣ | Ḍ Ṇ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | Ḍ Ṇ | S Ṇ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S S | Ṇ S ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S R | G M ||
S R G M | P D | N Ṡ ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with relaxed vocal chords, grounding the lower notes from the chest and lower abdomen.
            """.trimIndent(),
            meaning = "Extends lower octave range to Mandra Panchamam (P̣). Key exercise for vocal depth, morning sadhana, and grounding the voice.",
            youtubeUrl = "https://www.youtube.com/watch?v=ombHkVjN79o",
            orderIndex = 4
        ),
        LessonEntity(
            id = "keel_5",
            categoryId = "keel_sthayi",
            title = "Keel Sthayi Varisai 5",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Ṡ N D P | M G | R S ||
S , , , | S , | , , ||
G R S Ṇ | Ḍ P̣ | Ṃ P̣ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | Ḍ P̣ | Ḍ Ṇ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | Ḍ Ṇ | S Ṇ ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S S | Ṇ S ||
S Ṇ S R | G M | P M ||
G R S Ṇ | S R | G M ||
S R G M | P D | N Ṡ ||

Singing Speed Guide (All 4 Speeds):
Sing in all 4 speeds (1st, 2nd, 3rd, and 4th Kalams) with relaxed vocal chords, grounding the lower notes from the chest and lower abdomen.
            """.trimIndent(),
            meaning = "The apex depth exercise of Carnatic foundation, descending down to Mandra Madhyamam (Ṃ). Cultivates immense breath stamina and rich chest resonance.",
            youtubeUrl = "https://www.youtube.com/watch?v=NpPrG_oUWBA",
            orderIndex = 5
        ),

        // --- Alankarams (Sapta Tala - Raga Mayamalavagowla) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "alankaram_1",
            categoryId = "alankaram",
            title = "Dhruva Tala Alankaram (Chatusra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Chatusra Jati Dhruva Tala (I4 + O + I4 + I4 = 14 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M | G R | S R G R | S R G M ||
R G M P | M G | R G M G | R G M P ||
G M P D | P M | G M P M | G M P D ||
M P D N | D P | M P D P | M P D N ||
P D N Ṡ | N D | P D N D | P D N Ṡ ||
Ṡ N D P | D N | Ṡ N D N | Ṡ N D P ||
N D P M | P D | N D P D | N D P M ||
D P M G | M P | D P M P | D P M G ||
P M G R | G M | P M G M | P M G R ||
M G R S | R G | M G R G | M G R S ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "14 Aksharas per avartana: 1 Chatusra Laghu (4) + 1 Dhrutam (2) + 2 Chatusra Laghus (4 + 4). Master singing 14 aksharas smoothly across all 3 speeds.",
            youtubeUrl = "https://www.youtube.com/watch?v=ppzlAL8pzyU",
            orderIndex = 1
        ),
        LessonEntity(
            id = "alankaram_2",
            categoryId = "alankaram",
            title = "Matya Tala Alankaram (Chatusra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Chatusra Jati Matya Tala (I4 + O + I4 = 10 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G R | S R | S R G M ||
R G M G | R G | R G M P ||
G M P M | G M | G M P D ||
M P D P | M P | M P D N ||
P D N D | P D | P D N Ṡ ||
Ṡ N D N | Ṡ N | Ṡ N D P ||
N D P D | N D | N D P M ||
D P M P | D P | D P M G ||
P M G M | P M | P M G R ||
M G R G | M G | M G R S ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "10 Aksharas per avartana: 1 Chatusra Laghu (4) + 1 Dhrutam (2) + 1 Chatusra Laghu (4). Develops steady rhythmic balance with central Dhrutam.",
            youtubeUrl = "https://www.youtube.com/watch?v=x0DOBbbGRx0",
            orderIndex = 2
        ),
        LessonEntity(
            id = "alankaram_3",
            categoryId = "alankaram",
            title = "Rupaka Tala Alankaram (Chatusra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Chatusra Jati Rupaka Tala (O + I4 = 6 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R | S R G M ||
R G | R G M P ||
G M | G M P D ||
M P | M P D N ||
P D | P D N Ṡ ||
Ṡ N | Ṡ N D P ||
N D | N D P M ||
D P | D P M G ||
P M | P M G R ||
M G | M G R S ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "6 Aksharas per avartana: 1 Dhrutam (2) + 1 Chatusra Laghu (4). Essential foundation for hundreds of classic Carnatic Kritis and Gitams.",
            youtubeUrl = "https://www.youtube.com/watch?v=2TzRDXWdcfQ",
            orderIndex = 3
        ),
        LessonEntity(
            id = "alankaram_4",
            categoryId = "alankaram",
            title = "Jhampa Tala Alankaram (Misra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Misra Jati Jhampa Tala (I7 + U + O = 10 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G S R S R | G | M , ||
R G M R G R G | M | P , ||
G M P G M G M | P | D , ||
M P D M P M P | D | N , ||
P D N P D P D | N | Ṡ , ||
Ṡ N D Ṡ N Ṡ N | D | P , ||
N D P N D N D | P | M , ||
D P M D P D P | M | G , ||
P M G P M P M | G | R , ||
M G R M G M G | R | S , ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "10 Aksharas per avartana: 1 Misra Laghu (7) + 1 Anudhrutam (1) + 1 Dhrutam (2). Introduces the unique single-count clap (Anudhrutam U) and 7-count Laghu.",
            youtubeUrl = "https://www.youtube.com/watch?v=GasKyLIXI4U",
            orderIndex = 4
        ),
        LessonEntity(
            id = "alankaram_5",
            categoryId = "alankaram",
            title = "Triputa Tala Alankaram (Tisra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Tisra Jati Triputa Tala (I3 + O + O = 7 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G | S R | G M ||
R G M | R G | M P ||
G M P | G M | P D ||
M P D | M P | D N ||
P D N | P D | N Ṡ ||
Ṡ N D | Ṡ N | D P ||
N D P | N D | P M ||
D P M | D P | M G ||
P M G | P M | G R ||
M G R | M G | R S ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "7 Aksharas per avartana: 1 Tisra Laghu (3) + 2 Dhrutams (2 + 2). Direct sibling to Adi Tala (which is Chatusra Jati Triputa Tala - 8 beats).",
            youtubeUrl = "https://www.youtube.com/watch?v=hf0gM6HOKKU",
            orderIndex = 5
        ),
        LessonEntity(
            id = "alankaram_6",
            categoryId = "alankaram",
            title = "Ata Tala Alankaram (Khanda Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Khanda Jati Ata Tala (I5 + I5 + O + O = 14 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R , G , | S , R G , | M , | M , ||
R G , M , | R , G M , | P , | P , ||
G M , P , | G , M P , | D , | D , ||
M P , D , | M , P D , | N , | N , ||
P D , N , | P , D N , | Ṡ , | Ṡ , ||
Ṡ N , D , | Ṡ , N D , | P , | P , ||
N D , P , | N , D P , | M , | M , ||
D P , M , | D , P M , | G , | G , ||
P M , G , | P , M G , | R , | R , ||
M G , R , | M , G R , | S , | S , ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "14 Aksharas per avartana: 2 Khanda Laghus (5 + 5) + 2 Dhrutams (2 + 2). Crucial prerequisite for singing complex Ata Tala Varnams.",
            youtubeUrl = "https://www.youtube.com/watch?v=9xtt_LnkqVo",
            orderIndex = 6
        ),
        LessonEntity(
            id = "alankaram_7",
            categoryId = "alankaram",
            title = "Eka Tala Alankaram (Chatusra Jati)",
            raga = "Mayamalavagowla (15th Melakarta)",
            tala = "Chatusra Jati Eka Tala (I4 = 4 Aksharas)",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
S R G M ||
R G M P ||
G M P D ||
M P D N ||
P D N Ṡ ||
Ṡ N D P ||
N D P M ||
D P M G ||
P M G R ||
M G R S ||

Singing Speed Guide (All 3 Speeds):
Practice singing in 1st speed (1 note per akshara), 2nd speed (2 notes per akshara), and 3rd speed (4 notes per akshara) while strictly maintaining the Tala angas with your hands.
            """.trimIndent(),
            meaning = "4 Aksharas per avartana: 1 Chatusra Laghu (4). Fundamental building block representing 1 akshara per beat in the simplest cycle.",
            youtubeUrl = "https://www.youtube.com/watch?v=eC1j_d9MEW4",
            orderIndex = 7
        ),

        // --- Gitams (Foundational Melodic Compositions) - From carnatic.askharekrishna.com ---
        LessonEntity(
            id = "geetham_1",
            categoryId = "gitam",
            title = "Geetham 1: Sri Gananatha (Lambodara) - Malahari Ragam",
            raga = "Malahari (Janyam of 15th Meḷa Mayamalavagowla)",
            tala = "Chatuśra Jāti Rūpaka (2 + 4 = 6 Akṣharās)",
            arohana = "S R₁ M₁ P D₁ Ṡ",
            avarohana = "Ṡ D₁ P M₁ G₂ R₁ S",
            swaras = """
Charanam 1:
M  P  | D  Ṡ  Ṡ  Ṙ  || Ṙ  Ṡ  | D  P  M  P  ||
śrī - | ga ṇa nā tha || sin dū | -  ra var ṇa ||

R  M  | P  D  M  P  || D  P  | M  G  R  S  ||
ka ru | ṇā sā ga ra || ka ri | va da -  nā ||

S  ,  | R  M  G  R  || S  R  | G  R  S  ,  ||
lam - | bō -  da ra || la ku | mi ka rā -  ||

R  M  | P  D  M  P  || D  P  | M  G  R  S  ||
am -  | bā -  su ta || a  ma | ra vi nu ta ||

S  ,  | R  M  G  R  || S  R  | G  R  S  ,  ||
lam - | bō -  da ra || la ku | mi ka rā -  ||

Charanam 2:
M  P  | D  Ṡ  Ṡ  Ṙ  || Ṙ  Ṡ  | D  P  M  P  ||
sid dha | chā - ra ṇa || ga na | sē - vi ta ||

R  M  | P  D  M  P  || D  P  | M  G  R  S  ||
sid dhi | vi nā ya ka || tē - | na mō na mō (lam) ||

Charanam 3:
M  P  | D  Ṡ  Ṡ  Ṙ  || Ṙ  Ṡ  | D  P  M  P  ||
sa ka | la vi dyā - || -  dhi | pū - ji ta ||

R  M  | P  D  M  P  || D  P  | M  G  R  S  ||
sa -  | rvō - tta ma || tē - | na mō na mō (lam) ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Śrī gaṇanātha sindhūra varṇa karuṇā sāgara kari vadana |
Lambōdara laku mikara ambāsuta amara vinuta ||
Siddha chāraṇa gaṇa sēvita siddhi vināyaka tē namō namō |
Sakala vidyā ādi pūjita sarvōttama tē namō namō ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Śrī: Auspicious, glorious
• Gaṇanātha: Lord of the celestial attendants (ganas)
• Sindhūra varṇa: Having the radiant vermilion / red hue
• Karuṇā sāgara: Ocean of boundless compassion
• Kari vadana: Possessing the noble elephant countenance
• Lambōdara: He with the expansive belly (holding universes)
• Laku mikara (Lakṣmī-kara): Bestower of spiritual and material prosperity
• Ambāsuta: Dear son of Mother Ambā (Pārvatī)
• Amara vinuta: Praised and revered by the immortal devas
• Siddha chāraṇa gaṇa sēvita: Served by mystics (siddhas), celestial singers (charanas), and divine hosts
• Siddhi vināyaka: The lord who grants fulfillment of pure spiritual desires
• Tē namō namō: Salutations and repeated obeisances unto You
• Sakala vidyā: All forms of sacred learning and arts
• Ādi pūjita: Worshipped first before all auspicious beginnings
• Sarvōttama: Foremost among divine guides and protectors
• Tē namō namō: Repeated reverent salutations unto You

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O auspicious Sri Gananatha, resplendent with the vermilion color of dawn, an ocean of boundless compassion with the noble countenance of an elephant! O Lambodara, bestower of prosperity, son of Mother Amba, and praised by all immortal devas! You who are served by the Siddhas, Charanas, and celestial hosts, O Siddhi Vinayaka, repeated salutations unto You! You who are worshipped at the very inception of all branches of sacred learning, O foremost guide, our reverent obeisances unto You again and again!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Pillari Gitam praising Lord Ganesha, the remover of obstacles. Traditionally the first composition taught after swara exercises.",
            youtubeUrl = "https://www.youtube.com/watch?v=brHhKgT1BC4",
            orderIndex = 1
        ),
        LessonEntity(
            id = "geetham_2",
            categoryId = "gitam",
            title = "Geetham 2: Kundagowra Gavarivara - Malahari Ragam",
            raga = "Malahari (Janyam of 15th Meḷa Mayamalavagowla)",
            tala = "Chatuśra Jāti Rūpaka (2 + 4 = 6 Akṣharās)",
            arohana = "S R₁ M₁ P D₁ Ṡ",
            avarohana = "Ṡ D₁ P M₁ G₂ R₁ S",
            swaras = """
Charanam 1:
D  P  | M  G  R  S  || R  M  | P  D  M  P  ||
kun da | gau - -  ra || gau - | rī -  va ra ||

D  Ṙ  | Ṙ  Ṡ  D  P  || D  P  | M  G  R  S  ||
man di | rā - -  ya || mā - | na ma ku ṭa ||

S  ,  | R  ,  R  ,  || D  P  | M  G  R  S  ||
man - | dā -  ra -  || ku su | mā -  ka ra ||

S  R  | M  ,  G  R  || S  R  | G  R  S  ,  ||
ma ka | ran - dam - || vā - | si tu vā - ||

Charanam 2:
D  P  | M  G  R  S  || R  M  | P  D  M  P  ||
hē -  | ma kū -  ṭa || sim - | hā -  sa na ||

D  Ṙ  | Ṙ  Ṡ  D  P  || D  P  | M  G  R  S  ||
vi rū | pā -  -  kṣha || ka ru | ṇā -  ka ra || (mandāra)

Charanam 3:
D  P  | M  G  R  S  || R  M  | P  D  M  P  ||
chan da | mā - -  ma || man - | dā -  ki ni ||

D  Ṙ  | Ṙ  Ṡ  D  P  || D  P  | M  G  R  S  ||
man di | rā - -  ya || mā - | na ma ku ṭa || (mandāra)

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Kunda gowra gaurīvara mandirāya mānamakuta |
Mandāra kusumāsana makaranda vāsita ||
Hēma kūṭa simhāsana virūpākṣa karuṇākara |
Chanda māmā mandākinī mandira hara śambhō ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Kunda gowra: Pure and radiant white like the jasmine (kunda) flower
• Gaurīvara: The beloved consort of Mother Gaurī
• Mandirāya: Dwelling within the sanctum of the heart
• Mānamakuta: Adorned with a glorious crown of supreme honor
• Mandāra kusumāsana: Seated upon the sacred celestial Mandāra flower
• Makaranda vāsita: Fragrant with celestial honey nectar
• Hēma kūṭa simhāsana: Seated upon the golden throne of Mount Hemakuta
• Virūpākṣa: The great Lord with unique, all-seeing eyes (Virupaksha of Hampi)
• Karuṇākara: Embodiment and bestower of divine mercy
• Chanda māmā: Adorned with the gentle, luminous crescent moon
• Mandākinī mandira: In whose matted locks resides the celestial river Mandakini (Ganga)
• Hara śambhō: O auspicious Lord who removes material suffering, salutations unto You

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Lord, fair and radiant like the pure white jasmine blossom, beloved consort of Mother Gauri! You who wear a crown of supreme majesty and are seated amidst the sweet nectar-fragrant Mandara blossoms! O Virupaksha, merciful Lord seated upon the golden throne of Hemakuta! You who bear the gentle crescent moon and the sacred celestial river Mandakini (Ganga) upon Your head, O auspicious Hara Shambho, we offer our reverent salutations unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Second Pillari Gitam praising Lord Shiva (the white-complexioned one who holds the Ganga and is beloved of Gauri).",
            youtubeUrl = "https://www.youtube.com/watch?v=wyWxRPxfJNE",
            orderIndex = 2
        ),
        LessonEntity(
            id = "geetham_3",
            categoryId = "gitam",
            title = "Geetham 3: Keraya Neeranu - Malahari Ragam",
            raga = "Malahari (Janyam of 15th Meḷa Mayamalavagowla)",
            tala = "Tiśra Jāti Tripuṭa (3 + 2 + 2 = 7 Akṣharās)",
            arohana = "S R₁ M₁ P D₁ Ṡ",
            avarohana = "Ṡ D₁ P M₁ G₂ R₁ S",
            swaras = """
Charanam 1:
D  Ṡ  Ṡ  | D  P  | M  P  || D  D  P  | M  M  | P  ,  ||
ke re ya | nī -  | ra nu || ke re ye | chal - | li -  ||

D  D  Ṡ  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
va ra va | pa ḍe | da va || ran - ti | kā -  | ṇi rō ||

S  R  R  | S  R  | S  R  || D  D  P  | M  G  | R  S  ||
ha ri ya | ka ru | ṇa do || ḷā - da | bhā - | gya va ||

D  P  D  | Ṡ  ,  | D  P  || D  D  P  | M  G  | R  S  ||
ha ri sa | ma -  | rpa ṇa || mā - ḍi | ba du | ki rō ||

S  R  R  | S  R  | S  R  || D  D  P  | M  G  | R  S  ||
ha ri ya | ka ru | ṇa do || ḷā - da | bhā - | gya va ||

Charanam 2:
D  Ṡ  Ṡ  | D  P  | M  P  || D  D  P  | M  M  | P  ,  ||
śrī - pu | ran - | da ra || vi ṭṭha la | rā - | yā - ||

D  D  Ṡ  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
cha ra ṇa | ka ma | la va || nō - ḍi | ba tu | ki rō ||

S  R  R  | S  R  | S  R  || D  D  P  | M  G  | R  S  ||
ha ri ya | ka ru | ṇa do || ḷā - da | bhā - | gya va ||

D  P  D  | Ṡ  ,  | D  P  || D  D  P  | M  G  | R  S  ||
ha ri sa | ma -  | rpa ṇa || mā - ḍi | ba du | ki rō ||

S  R  R  | S  R  | S  R  || D  D  P  | M  G  | R  S  ||
ha ri ya | ka ru | ṇa do || ḷā - da | bhā - | gya va ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Kereya nīranu kerege chelli varava paḍedavarante kāṇirō |
Hari samārpita māḍi bāḷirō ||
Śrī purandara viṭṭhalana charaṇa kamalava nambirō ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Kereya nīranu: The water belonging to the lake / reservoir
• Kerege chelli: Pouring / offering it back unto the lake itself
• Varava: Divine boon and grace
• Paḍedavarante: Like those who have obtained
• Kāṇirō: Behold and understand, O humanity!
• Hari: Unto Supreme Bhagavan Sri Hari
• Samārpita māḍi: Offering everything in loving, surrendered devotion
• Bāḷirō: Live your life fruitfully
• Śrī purandara viṭṭhalana: Of Bhagavan Sri Purandara Vitthala (Sri Krishna)
• Charaṇa kamalava: The divine lotus feet
• Nambirō: Trust firmly and place unconditional faith in

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
Behold, O people! Just as one scoops water from a lake and pours it back into the lake to offer prayers and obtain blessings, similarly, whatever possessions, talents, and breath we have belong originally to Supreme Bhagavan Sri Hari; offer them back to Him in devoted surrender and live a pure, blessed life! Place unshakeable faith and take eternal shelter at the divine lotus feet of Sri Purandara Vitthala (Bhagavan Sri Krishna)!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Third Pillari Gitam composed by Purandara Dasa. Teaches offering the lake water back to the lake with sincere devotion.",
            youtubeUrl = "https://www.youtube.com/watch?v=omLGJxJ6x04",
            orderIndex = 3
        ),
        LessonEntity(
            id = "geetham_4",
            categoryId = "gitam",
            title = "Geetham 4: Padumanabha Paramapurusha - Malahari Ragam",
            raga = "Malahari (Janyam of 15th Meḷa Mayamalavagowla)",
            tala = "Tiśra Jāti Tripuṭa (3 + 2 + 2 = 7 Akṣharās)",
            arohana = "S R₁ M₁ P D₁ Ṡ",
            avarohana = "Ṡ D₁ P M₁ G₂ R₁ S",
            swaras = """
Charanam 1:
R  S  Ḍ  | S  ,  | S  ,  || M  G  R  | M  M  | P  ,  ||
pa du ma | nā -  | bhā - || pa ra ma | pu ru | ṣhā - ||

S  D  ,  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
pa ram-  | jō -  | -  ti || sva rū - | pā -  | -  -  ||

R  S  Ḍ  | S  ,  | S  ,  || M  G  R  | M  M  | P  ,  ||
vi du ra | van - | dyā - || vi ma la | cha ri| tā -  ||

S  D  ,  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
vi haṅ-  | gā -  | -  di || rō - ha  | ṇā -  | -  -  ||

P  M  P  | D  Ṡ  | D  Ṡ  || Ṙ  Ṡ  D  | D  Ṡ  | D  P  ||
u  da dhi| ni vā | -  sa || u  ra ga | śa ya | -  na ||

D  D  P  | P  ,  | P  M  || R  M  M  | P  ,  | P  ,  ||
un -  na | tōn - | na ta || ma hi -  | mā -  | -  -  ||

D  D  P  | P  ,  | P  M  || R  ,  M  | M  G  | R  S  ||
ya du ku | lō -  | tta ma|| ya - jnya| ra -  | kṣha ka||

S  ,  S  | D  D  | D  P  || P  ,  P  | M  G  | R  S  ||
yaj - nya| śi -  | kṣhaka|| rā - ma  | nā -  | -  mā  || (paduma)

Charanam 2:
D  Ṡ  ,  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
vi bhī - | ṣha ṇa| pā -  || la kā -  | na mō | na mō  ||

D  Ṡ  ,  | D  P  | M  P  || D  D  P  | M  G  | R  S  ||
i  bhā - | va ra | dā -  || ya kā -  | na mō | na mō  ||

P  M  P  | D  Ṡ  | D  Ṡ  || Ṙ  Ṡ  D  | D  Ṡ  | D  P  ||
śu bha - | pra da| su ma || nō - ra  | thā - | -  su  ||

D  D  P  | P  ,  | P  M  || R  M  M  | P  ,  | P  ,  ||
rē - ndra| ma -  | nō -  || ran - ja | nā -  | -  -  ||

D  D  P  | P  ,  | P  M  || R  ,  M  | M  G  | R  S  ||
a  bhi - | na -  | va pu || ran - da | ra -  | vi -  ||

S  ,  S  | D  D  | D  P  || P  ,  P  | M  G  | R  S  ||
ṭhal- la | bal - | la rē || rā - ma  | nā -  | -  mā  ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Padumanābha paramapuruṣha paranjyōti svarūpa |
Vidhi bhavādi vandya charaṇa vihangama rāja vāhana ||
Ibhāvaradāyaka namō namō |
Śubhaprada sumanōratha sundarendra manōranjanā ||
Abhinava purandara viṭṭhalla ballarē rāma nāmā ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Padumanābha: O Lord from whose navel springs the cosmic lotus
• Paramapuruṣha: The Supreme Personality of Godhead (Purushottama)
• Paranjyōti svarūpa: The transcendental form of supreme spiritual light
• Vidhi bhavādi: Lord Brahmā, Lord Shiva, and all the foremost devas
• Vandya charaṇa: Whose divine lotus feet are reverently worshipped by all
• Vihangama rāja vāhana: Who rides upon Garuḍa, the king of birds
• Ibhāvara dāyaka: Bestower of boons and liberation to the elephant Gajendra
• Namō namō: Salutations and repeated prostrations unto You
• Śubhaprada: Giver of all auspiciousness and spiritual welfare
• Sumanōratha: Fulfiller of noble, pure desires
• Sundarēndra manōranjanā: Delighting the hearts of saintly souls and sages
• Abhinava purandara viṭṭhalla: The ever-fresh Bhagavan Purandara Vitthala
• Ballarē rāma nāmā: Truly knows the supreme potency and glory of Sri Rama's holy name

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Padmanabha, Supreme Personality of Godhead, embodiment of divine transcendental light! Your lotus feet are revered by Brahma, Shiva, and all the devas, and You ride upon Garuda, the king of birds! O merciful savior who bestowed liberation upon Gajendra the elephant, repeated salutations unto You! You who grant all auspiciousness, fulfill the spiritual yearnings of pure souls, and enchant all saintly hearts—Bhagavan Sri Purandara Vitthala truly knows the supreme power and glory of chanting the holy name of Sri Rama!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Fourth Pillari Gitam praising Lord Padmanabha (Vishnu). Concludes the foundational Malahari Gitam quartet.",
            youtubeUrl = "https://www.youtube.com/watch?v=T6d2qvO69oQ",
            orderIndex = 4
        ),
        LessonEntity(
            id = "geetham_5",
            categoryId = "gitam",
            title = "Geetham 5: Annalekara - Suddha Saveri Ragam",
            raga = "Śuddha Sāveri (Janyam of 29th Meḷa Sankarabharanam)",
            tala = "Tiśra Jāti Tripuṭa (3 + 2 + 2 = 7 Akṣharās)",
            arohana = "S R₂ M₁ P D₂ Ṡ",
            avarohana = "Ṡ D₂ P M₁ R₂ S",
            swaras = """
Charanam 1:
Ṙ  M  Ṙ  | Ṙ  Ṡ  | D  Ṡ  || Ṡ  ,  Ṡ  | D  P  | M  P  ||
ā  -  na | lē -  | ka ra || vun - ni | pō -  | la di ||

D  D  Ṡ  | D  ,  | D  P  || P  M  R  | D  D  | D  P  ||
sa ka la | śā -  | stra pu || rā - ṇa | di -  | nam - ||

P  ,  P  | D  D  | D  P  || P  ,  P  | M  P  | D  P  ||
tā -  ḷa | di -  | nam - || tā -  ḷa | pa ri | ga tu ||

P  M  R  | S  R  | S  R  || P  M  P  | S  R  | S  R  ||
rē -  rē | a  -  | -  -  || a  -  -  | -  -  | -  -  ||

P  P  D  | P  P  | M  R  || R  S  R  | M  ,  | M  ,  ||
a  -  -  | -  -  | -  -  || sē -  tu | vā -  | ha -  ||

D  P  D  | Ṡ  ,  | Ṡ  ,  || Ṙ  Ṙ  Ṡ  | D  P  | M  P  ||
pa ri ga | tam - | nam - || ja ṭā -  | jū -  | -  ṭa ||

D  D  Ṡ  | D  ,  | D  P  || P  M  R  | D  D  | D  P  ||
sa ka la | śā -  | stra pu || rā - ṇa | di -  | nam - ||

P  ,  P  | D  D  | D  P  || P  ,  P  | M  P  | D  P  ||
tā -  ḷa | di -  | nam - || tā -  ḷa | pa ri | ga tu ||

P  M  R  | S  R  | S  R  || P  M  P  | S  R  | S  R  ||
rē -  rē | a  -  | -  -  || a  -  -  | -  -  | -  -  ||

P  P  D  | P  P  | M  R  || R  S  R  | M  ,  | M  ,  ||
a  -  -  | -  -  | -  -  || sē -  tu | vā -  | ha -  ||

D  P  D  | Ṡ  ,  | Ṡ  ,  ||
pa ri ga | tam - | nam - ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Ānalēkara unni pōlāki sakala śāstra purāṇa dinam |
Tāḷadinam tāḷaparigatu rē rē sētu vāha parigatam nam ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Ānalēkara: O Supreme Lord, dispeller of sins and illusions
• Unni pōlāki: Shining with resplendent spiritual beauty
• Sakala śāstra purāṇa dinam: Sung continuously throughout all sacred scriptures and Puranas
• Tāḷadinam tāḷaparigatu: Perfectly attuned to the cosmic rhythmic cycles and musical measures (talas)
• Rē rē: O wandering soul!
• Sētu vāha: Bhagavan Sri Rama, who built the sacred bridge (Setu)
• Parigatam nam: Unto Him who carries surrendered souls across material existence, our obeisances

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O divine Lord, dispeller of all impurities and radiant with celestial beauty! You whose glories are sung continuously throughout all the sacred Sastras and Puranas, attuned to cosmic rhythm and divine music! O Lord Sri Ramachandra who built the holy Setu bridge and carries His surrendered devotees across the turbulent ocean of worldly life, we offer our humble obeisances unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Majestic Tisra Triputa Gitam in Suddha Saveri praising Lord Shiva, composed by Sri Purandara Dasa.",
            youtubeUrl = "https://www.youtube.com/watch?v=P_eC4mmNA4Y",
            orderIndex = 5
        ),
        LessonEntity(
            id = "geetham_6",
            categoryId = "gitam",
            title = "Geetham 6: Vara Veena - Mohanam Ragam",
            raga = "Mōhanam (Janyam of 28th Meḷa Harikambhoji)",
            tala = "Chatuśra Jāti Rūpaka (2 + 4 = 6 Akṣharās)",
            arohana = "S R₂ G₂ P D₂ Ṡ",
            avarohana = "Ṡ D₂ P G₂ R₂ S",
            swaras = """
Charanam 1:
G  G  | P  ,  P  ,  || D  P  | Ṡ  ,  Ṡ  ,  ||
va ra | vī -  ṇā -  || mṛ du | pā -  ṇī -  ||

Ṙ  Ṡ  | D  D  P  ,  || D  P  | G  G  R  ,  ||
va na | ru ha lō -  || cha na| rā -  ṇī -  ||

G  P  | D  Ṡ  D  ,  || D  P  | G  G  R  ,  ||
su ru | chi ra pam- || ba ra | vē -  nī -  ||

G  G  | D  P  G  ,  || P  G  | G  R  S  ,  ||
su ra | nu ta kal - || yā -  | -  -  ṇī -  ||

G  G  | G  G  R  G  || P  G  | P  ,  P  ,  ||
ni ru | pa ma śu bha|| gu ṇa | lō -  lā -  ||

G  G  | D  P  D  ,  || D  P  | Ṡ  ,  Ṡ  ,  ||
ni ra | ta ja yā -  || pra da| śī -  lā -  ||

D  Ġ  | Ṙ  Ṙ  Ṡ  Ṡ  || D  Ṡ  | D  D  P  ,  ||
va ra | dā -  pri ya|| raṅ ga| nā -  ya ki ||

G  P  | D  Ṡ  D  P  || D  P  | G  G  R  S  ||
vā ñ  | chi ta pha la|| dā - | -  -  ya ki ||

S  R  | G  ,  G  ,  || G  R  | P  G  R  ,  ||
sa ra | si -  jā -  || sa na | ja na nī -  ||

S  R  | S  G  R  S  ||
ja ya | ja ya ja ya ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Vara vīṇā mṛdu pāṇi vanaruhalōchana rāṇī |
Suruchira bambhara vēṇī suranutakalyāṇī ||
Nirata jayāprada śīlā varadāpriya ranganāyaki |
Vāñchita phaladāyikī sarasijāsana jananī jaya jaya ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Vara vīṇā: Holding the divine, sacred Veena
• Mṛdu pāṇi: With delicate, gentle hands
• Vanaruha lōchana: With eyes charming like blooming lotus petals
• Rāṇī: The divine queen / goddess
• Suruchira: Exquisitely charming and luminous
• Bambhara vēṇī: Possessing dark, wavy braids resembling black honeybees
• Suranuta kalyāṇī: The auspicious goddess extolled by all celestial beings
• Nirata jayāprada śīlā: Ever endowed with the nature of bestowing righteous victory
• Varadāpriya: Dear unto Bhagavan Varadaraja
• Ranganāyaki: O Goddess Ranganayaki (Mother Lakshmi / Sarasvati)
• Vāñchita phala dāyikī: The fulfiller of all pure spiritual aspirations
• Sarasijāsana jananī: Mother of Brahma (seated on the lotus) / Divine Mother
• Jaya jaya: Victory, all victory unto You!

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Divine Mother, holding the sublime celestial Veena in Your tender hands, with eyes as gentle as blooming lotus petals! You whose dark, braided hair shines like bees hover over flowers, and who are extolled as the embodiment of auspiciousness by all the devas! You who unfailingly grant spiritual victory, the beloved of Bhagavan, Goddess Ranganayaki! Bestower of all cherished desires and compassionate Mother, victory, all victory unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Celebrated Gitam in Audava raga Mohanam invoking Goddess Saraswati holding the divine veena with gentle hands.",
            youtubeUrl = "https://www.youtube.com/watch?v=NYI8PRQI4sY",
            orderIndex = 6
        ),
        LessonEntity(
            id = "geetham_7",
            categoryId = "gitam",
            title = "Geetham 7: Kamala Jadala - Kalyani Ragam",
            raga = "Kalyāṇi (65th Meḷa Mechakalyani)",
            tala = "Tiśra Jāti Tripuṭa (3 + 2 + 2 = 7 Akṣharās)",
            arohana = "S R₂ G₂ M₂ P D₂ N₂ Ṡ",
            avarohana = "Ṡ N₂ D₂ P M₂ G₂ R₂ S",
            swaras = """
Charanam 1:
Ṡ  Ṡ  Ṡ  | N  D  | N  Ṡ  || N  D  P  | D  P  | M  P  ||
ka ma la | jā -  | da ḷa || vi ma la | su na | ya na ||

G  M  P  | P  D  | D  N  || D  P  M  | P  G  | R  S  ||
ka ri va | ra da | ka ru || ṇā - mbu | dhē - | -  -  ||

Ḍ  Ḍ  Ḍ  | G  G  | G  ,  || M  P  ,  | M  G  | R  S  ||
ka ru ṇa | śa ra | dhē - || ka ma -  | lā -  | -  -  ||

R  ,  ,  | S  ,  | ,  ,  || G  M  P  | M  P  | D  P  ||
kā -  n  | tā -  | -  -  || kē -  śi | na ra | kā -  ||

N  D  P  | D  P  | M  P  || G  M  P  | P  D  | D  N  ||
su ra vi | bhē - | da na || va ra da | vē -  | -  la ||

D  P  M  | P  G  | R  S  || Ḍ  Ḍ  Ḍ  | G  G  | G  ,  ||
su ra pu | rō -  | tta ma|| ka ru ṇa | śa ra | dhē - ||

M  P  ,  | M  G  | R  S  || R  ,  ,  | S  ,  | ,  ,  ||
ka ma -  | lā -  | -  -  || kā -  -  | ntā - | -  -  ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Kamalā jadala vimala sunayana kari vadana vadana sakala suranutē |
Karuṇā śaradhē kamalā kāntā kēśi naraka sura vibhēdana ||
Varada vēla surapurōttama karuṇā śaradhē kamalākāntā ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Kamalā jadala: Soft and pristine like a lotus petal
• Vimala sunayana: Endowed with pure, enchanting eyes
• Kari vadana: Respected by Gajanana and celestial leaders
• Sakala suranutē: Revered and praised by all devas
• Karuṇā śaradhē: Ocean of boundless divine compassion
• Kamalā kāntā: Beloved consort of Mother Lakshmi (Bhagavan Vishnu / Krishna)
• Kēśi naraka sura vibhēdana: Vanquisher of the fierce demons Keshi, Narakasura, and adversaries of the devas
• Varada: Bestower of supreme spiritual boons
• Vēla: Timeless protector of righteousness
• Surapurōttama: Supreme Lord worshipped by all celestial beings

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Supreme Bhagavan, whose pristine, lotus-like eyes shower gentle mercy, revered by all celestial beings and devas! You are an endless ocean of compassion, the beloved Lord of Mother Lakshmi (Kamala-kanta)! O slayer of the fierce demons Keshi and Narakasura, and protector of the virtuous! O bestower of divine benedictions and Supreme Sovereign of all the worlds, ocean of mercy, salutations unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Gitam in the 65th Melakarta Mechakalyani praising Lord Madhava with lotus-petal eyes.",
            youtubeUrl = "https://www.youtube.com/watch?v=RHTMYpSIrK0",
            orderIndex = 7
        ),
        LessonEntity(
            id = "geetham_8",
            categoryId = "gitam",
            title = "Geetham 8: Janakasuta - Saveri Ragam",
            raga = "Sāvēri (Janyam of 15th Meḷa Mayamalavagowla)",
            tala = "Chatuśra Jāti Rūpakam (2 + 4 = 6 Akṣharās)",
            arohana = "S R₁ M₁ P D₁ Ṡ",
            avarohana = "Ṡ N₂ D₁ P M₁ G₂ R₁ S",
            swaras = """
Charanam 1:
Ḍ  S  | R  M  M  ,  || M  G  | G  ,  R  S  ||
ja na | ka su tā -  || ku cha| kuṅ - ku ma ||

G  ,  | R  R  G  ,  || R  R  | S  Ḍ  S  ,  ||
paṅ - | ki ta lāñ-  || cha nu| rē -  rē -  ||

D  D  | P  M  P  ,  || P  M  | G  R  S  R  ||
ba li | ha ru rē -  || kha ga| vā -  ha na ||

P  M  | G  R  R  M  || G  R  | S  ,  S  ,  ||
kā -  | ñchī - pu ri || ni la| yā -  -  -  ||

S  R  | S  ,  Ṇ  Ḍ  || S  R  | M  ,  G  R  ||
ka ri | ra -  kṣha ka|| bhu ja| vi -  kra ma||

M  ,  | P  D  P  M  || P  D  | P  ,  P  P  ||
kā -  | mi ta pha la|| dā -  | -  -  ya ka ||

R  R  | M  M  P  ,  || D  P  | D  P  P  M  ||
ka ri | va ra dā -  || kal - | yā -  -  ṇa ||

P  D  | Ṡ  ,  N  D  || N  D  | P  D  M  ,  ||
pe run| dē -  vi ma || nō -  | ha ru rē -  ||

D  P  | P  M  G  R  || R  M  | G  R  S  ,  ||
ka ri | gi ri ni -  || vā -  | -  -  su rē -||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Janakasutā kucha kuṅkuma paṅkita lāñchanu rē rē |
Bali haru rē khagavāhana kāñchīpuri nilayā ||
Kari rakṣhaka bhuja vikrama kāmita phaladāyaka |
Kari varadā kalyāṇa perundēvi manōharu rē ||
Karigiri nivāsu rē ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Janakasutā: Mother Sītā Devī (daughter of King Janaka)
• Kucha kuṅkuma paṅkita: Adorned and marked with the sacred red saffron (kumkuma) paste from Sita's bosom
• Lāñchanu rē rē: O Lord bearing this auspicious mark of pure divine love!
• Bali haru rē: O Lord who curbed the pride of and blessed King Bali (as Vamanadeva)
• Khagavāhana: O One who rides upon Garuda, the king of birds
• Kāñchīpuri nilayā: O resident of the sacred holy city of Kanchipuram
• Kari rakṣhaka: O savior of the elephant Gajendra in his hour of peril
• Bhuja vikrama: Possessing heroic valor in Your mighty arms
• Kāmita phaladāyaka: The benevolent fulfiller of all cherished prayers
• Kari varadā: Lord Varadaraja, grantor of boons
• Kalyāṇa: Supreme embodiment of all auspiciousness
• Perundēvi manōharu rē: O enchanting Lord and beloved consort of Mother Perundevi Thayar (Mahalakshmi)
• Karigiri nivāsu rē: O eternal resident of the holy hill of Karigiri (Hastagiri)!

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Supreme Bhagavan Varadaraja, marked with the auspicious saffron-vermilion (kumkuma) of Mother Sita Devi! O subduer of King Bali, who rides the celestial bird Garuda and resides in the sacred holy city of Kanchipuram! O savior of the elephant Gajendra, endowed with invincible valor in Your mighty arms and bestower of all cherished boons! O auspicious Lord Varada, beloved consort of Goddess Perundevi Thayar (Mahalakshmi), who resides eternally upon the sacred Karigiri hill, our reverent salutations unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Gitam in Saveri Ragam (Rupaka Tala) praising Janakasuta (Sita Devi) and Lord Sri Rama.",
            youtubeUrl = "https://www.youtube.com/watch?v=V0k24Pcbck0",
            orderIndex = 8
        ),
        LessonEntity(
            id = "geetham_9",
            categoryId = "gitam",
            title = "Geetham 9: Mandara Dhara - Kambhoji Ragam",
            raga = "Kāmbhōji (Janyam of 28th Meḷa Harikambhoji)",
            tala = "Chatuśra Jāti Tripuṭa (4 + 2 + 2 = 8 Akṣharās)",
            arohana = "S R₂ G₂ M₁ P D₂ Ṡ",
            avarohana = "Ṡ N₁ D₂ P M₁ G₂ R₂ S",
            swaras = """
Charanam 1:
Ṡ  ,  N  P  | D  D  | Ṡ  ,  ||
man - da ra | dha ra| rē -  ||

D  Ṡ  Ṙ  Ġ  | Ṁ  Ġ  | Ġ  Ṙ  ||
mō -  kṣha mu| rā - | -  rē ||

Ṡ  Ṙ  Ṡ  Ṡ  | N  N  | D  P  ||
dai - tya ku| lā -  | n ta ka||

D  D  P  M  | G  M  | P  ,  ||
pā -  va na | mū r  | tē -  ||

G  P  D  Ṡ  | N  N  | D  P  ||
pa da śu bha| rē -  | -  kha||

D  D  P  P  | M  G  | R  S  ||
ma ku ṭa ma | yū -  | -  ra ||

G  P  P  D  | D  Ṡ  | Ṡ  Ṙ  ||
ā  -  -  -  | -  -  | -  -  ||

Ṙ  Ṗ  Ṁ  Ġ  | Ṙ  Ġ  | Ṙ  Ṡ  ||
ā  -  -  -  | -  -  | -  -  ||

Ṡ  Ṙ  Ṡ  Ṡ  | N  N  | D  P  ||
dai - tya ku| lā -  | n ta ka||

D  D  P  M  | G  M  | P  ,  ||
pā -  va na | mū ru | tē -  ||

G  P  D  Ṡ  | N  N  | D  P  ||
pa da śu bha| rē -  | -  kha||

D  D  P  P  | M  G  | R  S  ||
ma ku ṭa ma | yū -  | -  ra ||

Ṡ  ,  N  P  | D  D  | Ṡ  ,  ||
man - da ra | dha ra| rē -  ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Mandara dhara rē mōkṣhamu rē daitya kulāntaka pāvana mūrutē |
Pada śubha rēkha makuṭa mayūra mandara dhara rē ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Mandara dhara rē: O Lord who lifted the great Mandara / Govardhana mountain
• Mōkṣhamu rē: Bestower of liberation from material bondage
• Daitya kulāntaka: Vanquisher of the demonic clans
• Pāvana mūrutē: The all-pure, sacred transcendental form
• Pada śubha rēkha: Whose divine lotus feet are marked with all-auspicious lines (lotus, flag, thunderbolt)
• Makuṭa mayūra: Adorned with a radiant peacock feather upon His crown (Sri Krishna)
• Mandara dhara rē: O lifter of the mountain, salutations unto You!

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Supreme Bhagavan Sri Krishna, who lifted the sacred mountain and grants liberation from material suffering! O vanquisher of the demonic forces and embodiment of absolute spiritual purity! Your divine lotus feet bear all-auspicious markings, and Your radiant crown is adorned with the peacock feather. O Mandara Dhara, we offer our heartfelt devotion unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Adi Tala Gitam in the majestic Harikambhoji janya Kambhoji, celebrating Sri Krishna holding Mount Mandara.",
            youtubeUrl = "https://www.youtube.com/watch?v=-UuCQBM80vc",
            orderIndex = 9
        ),
        LessonEntity(
            id = "geetham_10",
            categoryId = "gitam",
            title = "Geetham 10: Re Re Sri Ramachandra - Arabhi Ragam",
            raga = "Ārabhi (Janyam of 29th Meḷa Sankarabharanam)",
            tala = "Tiśra Jāti Tripuṭa (3 + 2 + 2 = 7 Akṣharās)",
            arohana = "S R₂ M₁ P D₂ Ṡ",
            avarohana = "Ṡ N₂ D₂ P M₁ G₂ R₂ S",
            swaras = """
Charanam 1:
P  ,  P  | M  M  | P  ,  || M  G  R  | S  R  | M  G  ||
rē -  rē | śrī - | rā -  || -  -  ma | chan -| -  -  ||

R  R  S  | S  Ḍ  | R  S  || R  ,  ,  | ,  ,  | S  R  ||
-  -  -  | -  -  | -  -  || drā-  -  | -  -  | ra ghu||

M  G  R  | R  S  | S  ,  || P  M  M  | P  ,  | P  ,  ||
vam-  śa | ti la | kā -  || rā -  gha| vēn - | drā - ||

P  M  P  | M  G  | R  R  || M  G  R  | S  R  | S  S  ||
ā  -  -  | -  -  | -  -  || ā  -  -  | -  -  | -  -  ||

S  Ḍ  R  | S  R  | S  S  || Ḍ  S  ,  | Ḍ  Ḍ  | Ḍ  P  ||
ā  -  -  | -  -  | -  -  || ā  -  -  | śri ta| ja na ||

P  M  P  | D  S  | S  ,  || R  S  R  | M  G  | R  R  ||
pō -  sha| ku -  | rē -  || sī -  -  | tā -  | -  ma ||

M  G  R  | M  M  | P  M  || P  ,  P  | P  ,  | P  ,  ||
nō -  -  | rañ - | ja nu || rē -  rē | dhī - | rā -  ||

P  M  P  | D  Ṡ  | Ṡ  Ṙ  || Ṁ  Ġ  Ṙ  | Ṡ  Ṙ  | Ṡ  Ṡ  ||
rā -  va | ṇā -  | su ra || an -  ta | ku -  | rē -  ||

Ṡ  D  Ṙ  | Ṡ  Ṙ  | Ṡ  Ṡ  || D  Ṡ  ,  | D  D  | D  P  ||
ā  -  -  | yi ya | yi ya || a  -  -  | yi ya | yi ya ||

P  M  P  | D  Ṡ  | Ṡ  ,  || Ṡ  ,  Ṡ  | D  D  | P  ,  ||
ā  -  -  | yi ya | rē -  || dī -  na | ja na | man - ||

P  M  P  | M  G  | R  R  ||
dā -  ru | mā -  | ma va ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Rē rē śrī rāmachandra raghuvaṁśa tilaka rāghava rājēndra dīna janōddhāraṇa |
Kōdaṇḍa hasta rāvaṇāntaka rē rē dīna jana mandāru māmava ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Rē rē: O!
• Śrī rāmachandra: Bhagavan Sri Ramachandra
• Raghuvaṁśa tilaka: The glorious ornament and crown of the Raghu dynasty
• Rāghava rājēndra: Descendant of King Raghu and King of kings
• Dīna janōddhāraṇa: Uplifter of the humble and fallen souls
• Kōdaṇḍa hasta: Wielding the divine bow Kodanda in His hand
• Rāvaṇāntaka: The slayer of the ten-headed demon Ravana
• Dīna jana mandāru: The wish-fulfilling Mandara tree for all surrendered devotees
• Māmava: Please protect and deliver me!

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Bhagavan Sri Ramachandra, the crest jewel of the solar Raghu dynasty and King of kings! O savior and uplifter of all distressed and humble souls! Bearing the mighty Kodanda bow in Your hand, You vanquished the tyrant Ravana. O divine Kalpavriksha tree fulfilling the spiritual longings of the surrendered devotees, please protect and deliver me!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Energetic Gitam in Arabhi ragam (Tisra Triputa Tala) praising Sri Ramachandra, composer Purandara Dasa.",
            youtubeUrl = "https://www.youtube.com/watch?v=B6is6w3BZiI",
            orderIndex = 10
        ),
        LessonEntity(
            id = "geetham_11",
            categoryId = "gitam",
            title = "Geetham 11: Kamala Sulochana - Anandabhairavi Ragam",
            raga = "Ānanda bhairavi (Janyam of 20th Meḷa Natabhairavi)",
            tala = "Chatuśra Jāti Tripuṭa (4 + 2 + 2 = 8 Akṣharās)",
            arohana = "S G₁ R₂ G₁ M₁ P D₂ P Ṡ",
            avarohana = "Ṡ N₁ D₂ P M₁ G₁ R₂ S",
            swaras = """
Charanam 1:
N  D  N  Ṡ  | Ṡ  ,  | N  Ṡ  ||
ka ma la su | lō -  | cha na||

Ġ  Ṙ  Ṡ  N  | N  D  | P  M  ||
vi ma la ta | ṭā -  | ki ni ||

P  P  P  D  | N  D  | P  M  ||
ma rā -  ḷa | gā -  | mi ni ||

M  P  M  P  | G  R  | S  ,  ||
ka ri ha ra | ma -  | dhyē -||

S  ,  Ṇ  ,  | S  G  | G  M  ||
bim - bā -  | -  -  | na na ||

G  M  P  M  | G  R  | S  ,  ||
vi du maṇ - | ḍa la | rē -  ||

P  ,  M  G  | M  ,  | G  R  ||
chan - da na| kuṅ - | ku ma ||

G  ,  R  Ṇ  | S  ,  | S  ,  ||
san - ka li | tā -  | -  -  ||

P  P  M  G  | M  M  | G  R  ||
pa ri ma ḷa | ka -  | stū ri||

G  G  R  Ṇ  | S  ,  | S  ,  ||
ti la ka dha| rē -  | rē -  ||

Charanam 2:
S  G  R  G  | M  G  | M  ,  ||
jā -  -  ji | śai - | lyā - ||

P  N  D  N  | P  D  | N  Ṡ  ||
ka cha ku cha| gha na| ja ga ||

Ġ  Ṙ  Ṡ  N  | N  D  | P  M  ||
nam - -  -  | bō -  | -  ja ||

P  P  P  D  | N  D  | P  M  ||
ma rā -  ḷa | gā -  | mi ni ||

M  P  M  P  | G  R  | S  ,  ||
ka ri ha ra | ma -  | dhyē -||

S  ,  Ṇ  ,  | S  G  | G  M  ||
bim - bā -  | -  -  | na na ||

G  M  P  M  | G  R  | S  ,  ||
vi du maṇ - | ḍa la | rē -  ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Kamala sulōchana vimala mukhāmbuja marāḷa gāmini karihara madhyē |
Bimbānana vidumaṇḍala rē ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Kamala sulōchana: Possessing beautiful, gentle eyes like blooming lotus petals
• Vimala mukhāmbuja: Having a spotless face charming like the fresh lotus flower
• Marāḷa gāmini: Walking with the graceful gait of a swan
• Karihara madhyē: Possessing an elegant, slender waist like a lion
• Bimbānana: Having lips as tender and red as the ripe bimba fruit
• Vidumaṇḍala rē: Shining with the soothing luminescence of the full moon disc

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
O Divine Mother, blessed with eyes as gentle and expansive as lotus petals and a countenance radiant like the spotless lotus! Graceful in gait like the royal swan, possessing an elegant waist and lips as tender and red as the ripe bimba fruit, glowing with the cool, benevolent luminescence of the full moon disc, all glories unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Adi Tala Gitam in the evocative vakra raga Anandabhairavi, praising the lotus-eyed Lord.",
            youtubeUrl = "https://www.youtube.com/watch?v=C5ZsbWQ6ufI",
            orderIndex = 11
        ),
        LessonEntity(
            id = "geetham_12",
            categoryId = "gitam",
            title = "Geetham 12: Minakshi Jaya Kamakshi - Sri Ragam",
            raga = "Śrī (Janyam of 22nd Meḷa Kharaharapriya)",
            tala = "Chatuśra Jāti Dhruva (4 + 2 + 4 + 4 = 14 Akṣharās: I₄ O₂ I₄ I₄)",
            arohana = "S R₂ M₁ P N₁ Ṡ",
            avarohana = "Ṡ N₁ P M₁ R₂ G₁ R₂ S",
            swaras = """
Charanam 1:
M  M  P  ,  | P  ,  | N  P  N  N  | Ṡ  ,  Ṡ  ,  ||
mī -  nā -  | kṣhī -| ja ya kā -  | mā -  kṣhī- ||

Ġ  Ṙ  Ṡ  Ṡ  | N  P  | M  P  N  N  | Ṡ  ,  Ṡ  ,  ||
kaṅ - ka ṭi | -  ka | vā -  mā -  | kṣhī , -  -  ||

Ṙ  ,  Ġ  Ṙ  | Ṡ  ,  | Ṙ  ,  Ṡ  ,  | Ṡ  Ṡ  N  P  ||
ā  -  pra ti| bhā - | pra - bhā - | vō -  nna ta||

P  Ṡ  N  P  | Ṡ  N  | P  M  P  N  | P  P  M  ,  ||
ma dhu ra ma| dhu ra| sā -  laṅ - | kā -  ra -  ||

R  ,  M  ,  | P  ,  | N  Ṡ  Ṙ  ,  | Ṙ  Ġ  Ṙ  Ṡ  ||
ōm -  kā -  | ra -  | ka li tā -  | lā -  -  pa ||

Ṙ  ,  Ṗ  Ṁ  | Ṗ  P  | Ṙ  P  P  Ṁ  | Ṙ  Ġ  Ṙ  Ṡ  ||
yu -  ddha mi| - tra| ma dhu kai -| -  -  ṭa bha||

Ġ  Ṙ  Ṡ  Ṡ  | N  P  | N  P  P  Ṁ  | R  G  R  S  ||
khaṇ - ḍa chaṇ| - ḍa| daṇ - da nu | jā -  -  ṇu || (mīnākṣhī jaya)

Charanam 2:
S  Ṇ  P  Ṇ  | Ṇ  S  | R  M  M  P  | N  P  P  M  ||
ma da yā -  | -  -  | -  -  va ḷu | yā -  na na ||

R  ,  P  P  | M  R  | R  G  R  S  | S  ,  S  ,  ||
kā -  rti kē | -  ya | ja na nī -  | ja -  yā -  ||

R  ,  R  G  | R  S  | Ṇ  S  R  G  | R  R  S  Ṇ  ||
kā -  tyā -  | ya ni| kā -  ḷi ru | drā -  -  ṇi ||

P  ,  Ṇ  ,  | S  ,  | M  P  N  N  | S  ,  S  ,  ||
vī -  ṇā -  | ni -  | kvā - -  -  | -  -  ṇī -  ||

Ṇ  S  R  G  | R  S  | S  R  M  P  | N  P  M  P  ||
kā -  ra ṇa  | ka ra | na kha śi kha| ran - ja ya||

P  N  P  ,  | M  ,  | P  P  M  ,  | R  G  R  S  ||
mu di rā -  | pam - | ma dhu rā - | pri ya yi ya||

R  P  M  R  | P  M  | R  M  M  P  | N  P  P  M  ||
ā  yi ya ti | yi ya | a  yi yam - | vā -  yi ya ||

R  M  P  N  | Ṡ  N  | P  N  Ṡ  Ṙ  | Ṙ  Ġ  Ṙ  Ṡ  ||
ā  yi ya ti | yi ya | a  yi yam - | vā -  yi ya ||

R  M  P  N  | P  M  | P  P  M  ,  | R  G  R  S  ||
a  -  -  -  | -  -  | am -  bō -  | yi ya yi ya ||

Ṡ  ,  Ṡ  Ṡ  | N  P  | N  P  P  M  | R  G  R  S  ||
chok - ka nā| -  tha| svā - -  mi | mū -  ru tē||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Mīnākṣhī jaya kāmākṣhī mudirāpaṁ madhurāpriya |
Chokkanātha svāmi mūrutē ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Mīnākṣhī: Mother Minakshi of Madurai (with fish-shaped, compassionate eyes)
• Jaya kāmākṣhī: All victory to Mother Kamakshi of Kanchipuram (fulfiller of pure desires)
• Mudirāpaṁ: Dispeller of sorrow and bestower of transcendental happiness
• Madhurāpriya: Beloved deity of the holy city of Madurai
• Chokkanātha: Bhagavan Sundaresvara / Chokkanatha (The Beautiful Lord)
• Svāmi mūrutē: Divine embodiment and eternal Lord

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
All victory unto Mother Minakshi and Mother Kamakshi, who bestow divine joy and dispel the gloom of worldly existence! Beloved deity of sacred Madurai, united with Lord Chokkanatha (Sundaresvara), the supreme embodiment of beauty and grace, we offer our humble obeisances unto You!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Dhruva Tala (14 aksharas) Gitam in the auspicious Mangala raga Sri Ragam, praising Goddesses Minakshi and Kamakshi.",
            youtubeUrl = "https://www.youtube.com/watch?v=0fJGu2b8CbA",
            orderIndex = 12
        ),
        LessonEntity(
            id = "geetham_13",
            categoryId = "gitam",
            title = "Geetham 13: Sri Ramachandra - Bhairavi Ragam",
            raga = "Bhairavi (Janyam of 20th Meḷa Natabhairavi)",
            tala = "Chatuśra Jāti Dhruva (4 + 2 + 4 + 4 = 14 Akṣharās: I₄ O₂ I₄ I₄)",
            arohana = "S G₁ R₂ G₁ M₁ P D₂ N₁ Ṡ",
            avarohana = "Ṡ N₁ D₁ P M₁ G₁ R₂ S",
            swaras = """
Charanam 1:
G  R  G  M  | P  ,  | M  G  R  G  | M  P  M  ,  ||
śrī - rā -  | ma -  | chan - drā -| śri ta pā - ||

P  D  N  N  | D  P  | M  N  D  P  | M  G  R  S  ||
-  ri jā -  | -  ta | sa ma -  -  | -  -  -  sta||

S  R  S  P  | M  P  | G  R  G  M  | G  G  R  S  ||
kal - -  yā | -  ṇa | gu ṇā -  bhi| rā -  -  ma ||

R  R  G  G  | M  M  | G  G  R  G  | M  P  M  M  ||
sī -  tā -  | mu khā| -  -  -  -  | mbhō - ru ha||

P  D  D  N  | N  Ṡ  | P  D  N  Ṡ  | Ṙ  Ġ  Ṙ  Ṡ  ||
chan - -  - | -  cha| rī -  -  -  | -  -  -  ka ||

N  Ṙ  Ṡ  Ġ  | Ṙ  Ṡ  | N  N  D  M  | P  D  N  Ṡ  ||
ni ran - ta | ram - | maṅ - ga ḷa | mā -  -  ta ||

P  D  P  Ṡ  | N  Ṡ  | P  D  M  P  | G  ,  R  S  ||
nō -  -  tu | -  -  | -  -  -  -  | -  -  -  -  ||

--------------------------------------------------
SAHITYAM (LYRICS):
--------------------------------------------------
Śrī rāmachandra raghuvaṁśa tilaka kalyāṇa guṇābhirāma |
Sītā mukhāmbhōruha chañcharīka nirantaraṁ maṅgaḷaṁ mātanōtu ||

--------------------------------------------------
WORD-BY-WORD MEANING (HALAGANNADA / SANSKRIT):
--------------------------------------------------
• Śrī rāmachandra: Bhagavan Sri Ramachandra
• Raghuvaṁśa tilaka: The crowning glory of the Raghu lineage
• Kalyāṇa guṇa abhirāma: Delighting all with His auspicious, transcendental virtues
• Sītā mukhāmbhōruha: The lotus face of Mother Sita Devi
• Chañcharīka: The celestial honeybee that relishes the nectar of that lotus face
• Nirantaraṁ: Eternally, without cessation
• Maṅgaḷaṁ mātanōtu: May He bestow supreme auspiciousness and spiritual welfare upon us!

--------------------------------------------------
FULL TRANSLATION (MEANING):
--------------------------------------------------
May Bhagavan Sri Ramachandra, the crowning glory of the Raghu dynasty and repository of all transcendental auspicious virtues, who is like a celestial honeybee constantly relishing the nectar of Mother Sita Devi's lotus face, bestow eternal auspiciousness, spiritual joy, and divine blessing upon us all!
--------------------------------------------------

Singing Speed Guide:
Practice singing swaram first in 1st speed and 2nd speed until note-intervals and Tala kriyas are firm, then sing the Sahityam with clear lyrical diction and devotional expression.
            """.trimIndent(),
            meaning = "Grand Dhruva Tala (14 aksharas) Gitam in the classic Sampurna raga Bhairavi, celebrating Lord Sri Ramachandra.",
            youtubeUrl = "https://www.youtube.com/watch?v=fCErDanAduk",
            orderIndex = 13
        ),

        LessonEntity(
            id = "geetham_16",
            categoryId = "gitam",
            title = "Geetham 16: Karunaik Kadalamudhe - Nattakurinji Ragam",
            raga = "Naattakurinji (28th Melakarta Harikambhoji Janyam)",
            tala = "Adi Tala (Chaturasra Jati Triputa Tala - 8 beats / 16 Avartanas)",
            arohana = "S R2 G3 M1 N2 D2 N2 P D2 N2 Ṡ",
            avarohana = "Ṡ N2 D2 M1 G3 M1 P G3 R2 S",
            swaras = """
Composed by: Tiger Varadachariar
Ragam: Naattakurinji (Janya of 28th Melakarta Harikambhoji)
Talam: Adi Tala (8 beats per Avartana • 16 Avartanas)
Arohanam: S R2 G3 M1 N2 D2 N2 P D2 N2 Ṡ
Avarohanam: Ṡ N2 D2 M1 G3 M1 P G3 R2 S

--------------------------------------------------
SWARAM & SAHITYAM (16 AVARTANAS):
--------------------------------------------------

Avartana 1:
Swaram:   N   D   N , | Ṡ   N   | D   N   ||
Sahityam: ka  ru  ṇaik| ka  ḍa  | la  mu  ||

Avartana 2:
Swaram:   Ṡ   ,   ,   | Ṡ   ,   | ,   ,   ||
Sahityam: dhē -   -   | nin -   | -   -   ||

Avartana 3:
Swaram:   N   Ṡ   N   N   | Ṡ   D   | N   Ṡ   ||
Sahityam: ka  zha li  nai | a   ḍain| dhi ḍa  ||

Avartana 4:
Swaram:   N   Ṡ   N   D   | N   P   | D   ,   ||
Sahityam: va  ra  ma  ruḷ | vā  -   | yē  -   ||

Avartana 5:
Swaram:   N   ,   Ṡ   N   | D   M   | G   R   ||
Sahityam: nin -   pe  ru  | mai ya  | ṟi  ya  ||

Avartana 6:
Swaram:   G   ,   M   P   | G   R   | S   ,   ||
Sahityam: en  -   va  sa  | mā  -   | mō  -   ||

Avartana 7:
Swaram:   M   G   R   G   | S   R   | Ṇ   Ḍ   ||
Sahityam: ni  lai pe  ṟu  | ka  lai | ma  ga  ||

Avartana 8:
Swaram:   Ṇ   Ḍ   Ṇ   S   | R   G   | S   ,   ||
Sahityam: ḷē  -   yu  naith| tho zhu | dhēn -   ||

Avartana 9:
Swaram:   M   G   M   ,   | M   N   | D   N   ||
Sahityam: ma  ṟai yā  -   | ga  ma  | mu  ḍan ||

Avartana 10:
Swaram:   P   D   N   Ṡ   | N   D   | N   ,   ||
Sahityam: mā  -   mu  ni  | vō  -   | rum -   ||

Avartana 11:
Swaram:   Ṡ   N   Ṡ   Ṙ   | Ġ   Ṡ   | Ṁ   Ġ   ||
Sahityam: ma  na  mu  ru  | gi  pu  | ka  zhum||

Avartana 12:
Swaram:   Ṁ   Ġ   Ṁ   Ṙ   | Ġ   Ṙ   | Ṡ   ,   ||
Sahityam: māṇ bu  peṟ ṟa  | va  ḷē  | -   -   ||

Avartana 13:
Swaram:   Ṡ   N   Ṡ   Ṙ   | Ġ   D   | N   Ṡ   ||
Sahityam: ma  dhi yu  ṟai | -   ko  | ḍi  yē  ||

Avartana 14:
Swaram:   Ṡ   N   D   M   | ,   G   | S   ,   ||
Sahityam: ma  naṅ ka  nin | -   dhē | en  -   ||

Avartana 15:
Swaram:   S   Ṇ   Ḍ   Ṇ   | P   Ḍ   | Ṇ   ,   ||
Sahityam: ma  nu  vi  nai | yēṟ -   | ṟu  -   ||

Avartana 16:
Swaram:   Ṇ   Ḍ   Ṇ   S   | R   ,   | S   ,   ||
Sahityam: va  ra  ma  ruḷ | thā -   | yē  -   ||

--------------------------------------------------
SAHITYAM (ENGLISH TRANSLITERATION):
--------------------------------------------------
Karunaik kadalamudhe nin kazhalinai adaindhida varamarul vaaye
Nin perumaiyariya en vasamaamo nilaiperu kalaimagale yunaith thozhudhene
Marai yaagamudan maamunivoorum manamurugip pugazhum maanbu petravale
Madhiyurai kodiye manam kanindhe en manuvinai etru varamarul thaaye

--------------------------------------------------
WORD-BY-WORD MEANING:
--------------------------------------------------
• Karunaik Kadalamudhe: O nectar born of the infinite ocean of compassion
• Nin kazhalinai: At your divine lotus feet
• Adaindhida varamarul vaaye: Graciously bestow the boon of surrender
• Nin perumai ariya: To comprehend your supreme magnificence
• En vasamaamo: Is it within my humble human capacity?
• Nilaiperu Kalaimagale: O eternal Goddess of Knowledge (Saraswati)
• Yunaith thozhudhene: I offer my heartfelt obeisances unto you
• Marai yaagamudan: Alongside sacred Vedic chants and holy rituals
• Maamunivoorum: Great realized sages and rishis
• Manamurugip pugazhum: Extolling your glory with melting hearts
• Maanbu petravale: O embodiment of sublime divine majesty
• Madhiyurai kodiye: O radiant creeper residing in pure intellect
• Manam kanindhe: With a tender, compassionate heart
• En manuvinai etru: Accepting my sincere prayer
• Varamarul thaaye: Bless me with your maternal grace, O Mother!

--------------------------------------------------
FULL TRANSLATION:
--------------------------------------------------
O Goddess of Knowledge (Kalaimagal), sweet nectar born of the ocean of divine compassion! Graciously grant me the boon to attain and surrender at your holy lotus feet. Is it within my humble power to comprehend your boundless greatness? I bow down in deep reverence unto you! Great sages along with the sacred Vedas sing your glory with hearts melting in devotion. O radiant creeper of pure wisdom, with a heart overflowing with maternal grace, accept my humble prayer and bless me, O Mother!

--------------------------------------------------
Singing Speed Guide:
Practice singing the swaram first in 1st speed (1 note per akshara) and 2nd speed (2 notes per akshara) with steady Adi Tala hand kriyas (4-beat Laghu + two 2-beat Dhrutams). Once notes and intervals are firm, sing the Sahityam with clear enunciation and devotional expression.
            """.trimIndent(),
            sahitya = """
Karunaik kadalamudhe nin kazhalinai adaindhida varamarul vaaye
Nin perumaiyariya en vasamaamo nilaiperu kalaimagale yunaith thozhudhene
Marai yaagamudan maamunivoorum manamurugip pugazhum maanbu petravale
Madhiyurai kodiye manam kanindhe en manuvinai etru varamarul thaaye
            """.trimIndent(),
            meaning = "Celebrated Tamil Geetham composed by Tiger Varadachariar in Raagam Naattakurinji (Adi Talam) invoking Goddess Saraswati / Kalaimagal.",
            youtubeUrl = "https://www.youtube.com/results?search_query=karunaik+kadalamudhe+geetham+nattakurinji",
            orderIndex = 16
        ),

        // --- Varnams ---
        LessonEntity(
            id = "varnam_ninnukori",
            categoryId = "varnam",
            title = "Ninnukori - Mohana Varnam",
            raga = "Mohanam (28th Melakarta Harikambhoji janya)",
            tala = "Adi Tala (8 aksharas)",
            arohana = "S R2 G3 P D2 Ṡ",
            avarohana = "Ṡ D2 P G3 R2 S",
            swaras = """
Pallavi:
G , G , | R , , , | S R G R | S R S D ||
S R G P | G R S R | G P G R | S R G P ||

Anupallavi:
P G D P | D Ṡ D Ṡ | Ṙ Ṡ D Ṡ | Ṙ Ġ Ṙ Ṡ ||
Ġ Ṙ Ṡ D | Ṡ D P G | D P G R | S R G P ||

Mukthayi Swaram:
G , R G | R S R , | S D S R | G P D , ||
P D Ṡ Ṙ | Ġ Ṙ Ṡ D | Ṙ Ṡ D P | G R S R ||
            """.trimIndent(),
            sahitya = "Ninnu kori yunna nura nikila loka nayaka...",
            meaning = "I long for you, O Lord of all worlds! Masterwork composed by Ramnad Srinivasa Iyengar.",
            youtubeUrl = "https://www.youtube.com/watch?v=NinnukoriMohanam",
            orderIndex = 1
        ),

        // --- Ragams ---
        LessonEntity(
            id = "ragam_mayamalavagowla",
            categoryId = "ragam",
            title = "Mayamalavagowla (Raga Swarupa & Alapana)",
            raga = "15th Melakarta Raga",
            tala = "Free-time Alapana / Manodharma",
            arohana = "S R1 G3 M1 P D1 N3 Ṡ",
            avarohana = "Ṡ N3 D1 P M1 G3 R1 S",
            swaras = """
Characteristic Phrases (Raga Prayogas):
• S , R1 , G3 , M1 , P ,
• P , D1 N3 Ṡ , Ṡ N3 D1 P ,
• M1 G3 R1 S , Ṇ3 Ḍ1 Ṇ3 S ,
• G3 M1 P D1 P , M1 G3 R1 S
            """.trimIndent(),
            meaning = "Symmetrical raga with consecutive semi-tones (S-R1, M1-P, N3-Ṡ). Ideal for morning practice and pitch grounding.",
            orderIndex = 1
        ),
        LessonEntity(
            id = "ragam_kalyani",
            categoryId = "ragam",
            title = "Mechakalyani (65th Melakarta)",
            raga = "Mechakalyani",
            tala = "Alapana",
            arohana = "S R2 G3 M2 P D2 N3 Ṡ",
            avarohana = "Ṡ N3 D2 P M2 G3 R2 S",
            swaras = """
Characteristic Phrases:
• G , M2 P D N Ṡ ,
• Ṡ N D P M2 , G R S ,
• R G M2 P , M2 G R S ,
• N3 R2 G3 M2 P D2 N3 Ṡ
            """.trimIndent(),
            meaning = "Majestic Prati Madhyama raga radiating grandeur, joy, and festive auspiciousness.",
            orderIndex = 2
        ),

        // --- Kritis ---
        LessonEntity(
            id = "kriti_vatapi",
            categoryId = "kritis",
            title = "Vatapi Ganapatim Bhajeham",
            raga = "Hamsadhwani (29th Melakarta Shankarabharanam janya: S R2 G3 P N3 Ṡ)",
            tala = "Adi Tala",
            arohana = "S R2 G3 P N3 Ṡ",
            avarohana = "Ṡ N3 P G3 R2 S",
            swaras = """
Pallavi:
Vatapi Ganapatim Bhajeham Varanaasyam Vara Pradam Sri...
            """.trimIndent(),
            sahitya = """
Pallavi:
Vatapi Ganapatim bhajeham varanasyam vara pradam shri

Anupallavi:
Bhootadi samsevita charanam bhoota bhoutika prapancha bharanam

Charanam:
Pranavakara kumba sambhava munivara prapujitam
            """.trimIndent(),
            meaning = "Muthuswami Dikshitar's immortal masterpiece on Lord Ganesha in Hamsadhwani.",
            youtubeUrl = "https://www.youtube.com/watch?v=VatapiGanapatim",
            orderIndex = 1
        ),
        LessonEntity(
            id = "kriti_maha_ganapatim",
            categoryId = "kritis",
            title = "Maha Ganapatim Manasa Smarami",
            raga = "Nattai (36th Melakarta Chalanattai janya)",
            tala = "Adi Tala (Chatusra Eka / Adi)",
            arohana = "S R3 G3 M1 P D3 N3 Ṡ",
            avarohana = "Ṡ N3 P M1 R3 S",
            sahitya = "Maha Ganapatim manasa smarami, vasishta vamadevadi vandita...",
            meaning = "Composed by Muthuswami Dikshitar invoking the wisdom and auspicious grace of Maha Ganapati.",
            orderIndex = 2
        )
    )

    fun getReferenceAudioUrl(lessonId: String): String? {
        return when (lessonId) {
            "sarali_1" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_1.mp3"
            "sarali_2" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/Sarali_varisai_2.mp3"
            "sarali_3" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_3.mp3"
            "sarali_4" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_4.mp3"
            "sarali_5" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_5_.mp3"
            "sarali_6" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_6.mp3"
            "sarali_7" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_7_.mp3"
            "sarali_8" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_8.mp3"
            "sarali_9" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_9.mp3"
            "sarali_10" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_10.mp3"
            "sarali_11" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_11.mp3"
            "sarali_12" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_12.mp3"
            "sarali_13" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_13.mp3"
            "sarali_14" -> "https://api.askharekrishna.com/media/carnatic_lesson_practice/sarali_varisai_14.mp3"
            else -> null
        }
    }
}

