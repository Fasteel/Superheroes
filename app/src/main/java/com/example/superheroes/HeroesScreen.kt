package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.model.Hero
import com.example.superheroes.data.repository.HeroesRepository
import com.example.superheroes.ui.theme.Shapes
import com.example.superheroes.ui.theme.SuperheroesTheme

@Composable
fun HeroesScreen() {
    Scaffold(
        topBar = { HeroTopBar() }
    ) { paddingValues ->
        HeroesList(
            heroes = HeroesRepository.heroes + HeroesRepository.heroes,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
        }
    )
}

@Composable
@Preview
fun HeroTopBarPreviewDark() {
    SuperheroesTheme(darkTheme = true) {
        HeroTopBar()
    }
}

@Composable
@Preview(showBackground = true)
fun HeroTopBarPreviewLight() {
    SuperheroesTheme(darkTheme = false) {
        HeroTopBar()
    }
}

@Composable
fun HeroesList(heroes: List<Hero>, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small)),
            contentPadding = PaddingValues(
                vertical = 0.dp,
                horizontal = dimensionResource(R.dimen.medium)
            )
        ) {
            items(heroes) {
                HeroItem(
                    name = it.nameRes,
                    description = it.descriptionRes,
                    image = it.imageRes,
                )
            }
        }
    }
}

@Preview
@Composable
fun HeroListPreviewDark() {
    SuperheroesTheme(darkTheme = true) {
        HeroesList(heroes = HeroesRepository.heroes)
    }
}

@Preview
@Composable
fun HeroListPreviewLight() {
    SuperheroesTheme(darkTheme = false) {
        HeroesList(heroes = HeroesRepository.heroes)
    }
}

@Composable
fun HeroItem(
    @StringRes name: Int,
    @StringRes description: Int,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Row(
            modifier = modifier
                .padding(dimensionResource(R.dimen.medium))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    stringResource(name),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = modifier.padding(bottom = dimensionResource(R.dimen.small))
                )
                Text(
                    stringResource(description),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(name),
                modifier = modifier
                    .height(72.dp)
                    .padding(start = dimensionResource(R.dimen.medium))
                    .clip(Shapes.small)
            )
        }
    }
}

@Preview
@Composable
fun HeroItemPreviewDark() {
    SuperheroesTheme(darkTheme = true) {
        HeroItem(
            name = R.string.hero1,
            description = R.string.description1,
            image = R.drawable.android_superhero1
        )
    }
}

@Preview
@Composable
fun HeroItemPreviewLight() {
    SuperheroesTheme(darkTheme = false) {
        HeroItem(
            name = HeroesRepository.heroes[0].nameRes,
            description = HeroesRepository.heroes[0].descriptionRes,
            image = HeroesRepository.heroes[0].imageRes,
        )
    }
}
