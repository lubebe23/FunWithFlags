// app/src/main/java/com/example/funwithflags/data/FlagEntity.kt
package com.example.funwithflags.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.example.funwithflags.R

@Entity(tableName = "flags")
data class FlagEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val countryName: String,

    @ColumnInfo(name = "image_res_id")
    val imageResId: Int,

    val description: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
) {
    //List of flags with a brief description
    companion object {
        val DEFAULT_FLAGS = listOf(
            FlagEntity(
                countryName = "Ireland",
                imageResId = R.drawable.flag_ie,
                description = "A green, white, and orange tricolor symbolizing peace between Catholics (green) and Protestants (orange)."
            ),
            FlagEntity(
                countryName = "Nigeria",
                imageResId = R.drawable.flag_ng,
                description = "Two green stripes (agriculture) flanking white (peace), reflecting unity in diversity."
            ),
            FlagEntity(
                countryName = "South Korea",
                imageResId = R.drawable.flag_sk,
                description = "White with a red-blue yin-yang and four trigrams, embodying balance and cosmic harmony."
            ),
            FlagEntity(
                countryName = "Sudan",
                imageResId = R.drawable.flag_sd,
                description = "Red (struggle), white (peace), black (Sudan), and green (Islam)—Pan-Arab colors representing unity."
            ),
            FlagEntity(
                countryName = "Austria",
                imageResId = R.drawable.flag_as,
                description = "Three horizontal stripes (red-white-red), said to originate from a medieval battle banner."
            ),
            FlagEntity(
                countryName = "China",
                imageResId = R.drawable.flag_ch,
                description = "Red for revolution, one gold star (Communist Party) and four smaller stars (social classes united under it)."
            ),
            FlagEntity(
                countryName = "The Netherlands",
                imageResId = R.drawable.flag_nd,
                description = "A red, white, and blue tricolor, one of the oldest national flags, inspiring many others."
            ),
            FlagEntity(
                countryName = "United Kingdom",
                imageResId = R.drawable.flag_uk,
                description = "The Union Jack merges England’s red cross, Scotland’s white diagonal cross, and Ireland’s red diagonal cross."
            ),
            FlagEntity(
                countryName = "United States",
                imageResId = R.drawable.flag_us,
                description = "Thirteen stripes for the original colonies, fifty stars for current states—representing unity and independence."
            ),
            FlagEntity(
                countryName = "India",
                imageResId = R.drawable.flag_in,
                description = "Saffron (courage), white (peace), and green (prosperity) with a blue Ashoka Chakra wheel for progress."
            )

        )
    }
}