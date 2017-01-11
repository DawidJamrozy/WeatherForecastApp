# WeatherForecastApp

Aplikacja umożliwiająca pobieranie oraz prezentacje aktualnych danych pogodowych. Stworzona z wykorzystaniem wzroca projektowego MVVM oraz Databinding.

Biblioteki wykorzystane do stworzenia aplikacji:
- Retrofit 2
- RxJava 2
- Dagger 2
- Retro Lamba
- Gson
- OkHttp
- Picasso
- MpAndroidCharts
- Timber
- Stetho
- Leak Canary
- Realm

Inne: 

- Google Play Services - AutoComplete
- Google Play Services - Reverse Geocoding
- Google Play Services - Details
- DarkSky API

Screeny:

<p align="center">
  <img src="https://s27.postimg.org/q2li8yj43/Screenshot_2017_01_11_15_10_42_083_com_dawidj_we.png" width="350"/>
  <img src="https://s27.postimg.org/3rxn8zltv/Screenshot_2017_01_11_15_10_52_437_com_dawidj_we.png" width="350"/>
  <img src="https://s27.postimg.org/bypn0kbwj/Screenshot_2017_01_11_15_11_47_336_com_dawidj_we.png" width="350"/>
  <img src="https://s27.postimg.org/83m8xzsqr/Screenshot_2017_01_11_15_11_58_964_com_dawidj_we.png" width="350"/>
</p>

Funkcjonalność:

- Określenie lokalizacji i dodanie miasta za pomocą połączenia internetowego lub GPS (Google Reverse Geocoding),
- Wyszukiwanie miasta poprzez wpisanie jego nazwy (Google Autocomplete oraz Google Details),
- Pobieranie danych pogodowych (DarkSky API),
- Przechowywanie danych pogodowych w bazie lokalnej na urządzeniu mobilnym (Realm),
- Wyświetlanie danych pogodowych, szczegółowa prognoza pogodowa (obecna) oraz prognoza tygodniowa.

Aplikacja obsługuje język Polski oraz Angielski w zależności od ustawień systemowych urządzenia mobilnego.
