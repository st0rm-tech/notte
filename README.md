# Notte - Minimalist Not Defteri

macOS'tan ilham alan güzel, minimalist bir Android not alma uygulaması.

## Özellikler

- ✨ Temiz, minimalist arayüz
- 🎨 macOS'tan ilham alan tasarım
- ⚡ Hızlı ve akıcı performans
- 🔄 Otomatik kaydetme
- 🔍 Güçlü arama
- 🌙 Koyu mod desteği
- ♿ Tam erişilebilirlik

## Teknoloji Yığını

- **UI Framework**: Jetpack Compose (Material Design 3)
- **Programlama Dili**: Kotlin
- **Mimari**: MVVM + Clean Architecture
- **Veri Kalıcılığı**: Room Database
- **Bağımlılık Enjeksiyonu**: Hilt
- **Asenkron İşlemler**: Kotlin Coroutines + Flow

## Gereksinimler

- Android Studio Hedgehog | 2023.1.1 veya üzeri
- JDK 17
- Android SDK 24 (minimum) - 34 (target)

## Kurulum

1. Projeyi klonlayın
2. Android Studio'da açın
3. Gradle sync yapın
4. Uygulamayı çalıştırın

## Mimari

Proje Clean Architecture prensiplerine göre üç katmana ayrılmıştır:

```
app/
├── domain/          # İş mantığı ve entity'ler
│   ├── model/
│   ├── repository/
│   └── usecase/
├── data/            # Veri kaynakları ve implementasyonlar
│   ├── local/
│   └── repository/
└── presentation/    # UI ve ViewModels
    ├── notelist/
    └── noteeditor/
```

## Lisans

Bu proje özel bir projedir.

## İletişim

- Domain: com.notte.app
- Versiyon: 1.0.0
