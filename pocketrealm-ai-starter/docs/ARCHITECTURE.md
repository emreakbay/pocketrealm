# ARCHITECTURE.md — PocketRealm

## Architectural goal

Oyunun domain mantığını Android UI teknolojilerinden ayırmak; widget, normal uygulama ekranı ve gelecekteki yeni arayüzlerin aynı oyun motorunu kullanmasını sağlamak.

## High-level architecture

```text
                    User
                     │
          ┌──────────┴──────────┐
          │                     │
       App UI                Widget
          │                     │
          └──────────┬──────────┘
                     ▼
                 Use Cases
                     │
                     ▼
                Game Engine
                     │
             ┌───────┴───────┐
             ▼               ▼
        Repository       Event/Rules
             │
             ▼
          Room DB
```

## Layers

### Presentation

Compose screens, Glance widget, notifications and UI state mapping.

Presentation yalnızca use-case/domain API'leri üzerinden iş yapar.

### Domain

Saf oyun kuralları:

- PlayerState
- Resources
- GameEvent
- Decision
- DecisionOutcome
- Reward
- GameEngine

Domain mümkün olduğunca Android framework bağımsız tutulur.

### Data

- Room entities/DAO.
- Repository implementation.
- DataStore preferences.
- Serialization/mapping.

### Platform

Android-specific integrations:

- Widget refresh.
- Notifications.
- WorkManager.
- App lifecycle.

## Suggested package structure

```text
app/
core/
  common/
  model/
  database/
  data/
feature-game/
  ui/
  domain/
feature-widget/
  ui/
  actions/
feature-notification/
  notification/
platform/
  work/
```

Bu yapı mevcut kod tabanına göre sadeleştirilebilir; klasör yapısı dogma değildir.

## Data flow: widget action

1. Kullanıcı widget üzerindeki karara dokunur.
2. Widget action callback'i tetiklenir.
3. İlgili use case çağrılır.
4. Game Engine mevcut `PlayerState` üzerinden sonucu hesaplar.
5. Repository yeni state'i persist eder.
6. Widget güncellenir.
7. Gerekirse uygulama ekranı aynı state'i yeniden okur.

## Source of truth

Kalıcı oyuncu durumu veritabanıdır.

Widget cache veya geçici UI state, oyun ilerlemesinin kaynağı değildir.

## Determinism

Oyun kuralları mümkün olduğunca deterministik olmalıdır. Random event kullanılıyorsa random seed veya yeniden üretilebilir karar kaydı gibi bir yaklaşım düşünülmelidir. Amaç bug raporlarının tekrar üretilebilmesidir.

## Time model

Oyun zamanı ile gerçek zaman ayrılmalıdır.

Örn:

- `lastInteractionAt`
- `currentGameDay`
- `nextEventAt`

Gerçek zamanlı sistem davranışları ayrı bir policy katmanında tutulmalıdır.

## Background work

Background worker yalnızca gerekli güncellemeleri planlar. Sürekli polling yapılmaz.

## Error handling

- Kullanıcı ilerlemesi veri yazma hatasında sessizce kaybedilmemeli.
- Widget güncelleme başarısızlığı, domain işlemini otomatik olarak başarısız saymamalı.
- Recoverable hatalar kullanıcıya anlaşılır şekilde yansıtılmalı.

## Test strategy

### Unit tests

Game Engine, resource calculations, decisions, event outcomes.

### Integration tests

Repository + Room, persistence ve migration davranışları.

### UI/widget tests

Kritik kullanıcı akışları ve aksiyonlar.

### Manual device checks

Farklı Android sürümleri, küçük/büyük ekranlar ve widget boyutları.

## Dependency policy

Yeni dependency eklemek için:

1. Sorunu açıklayın.
2. Platformun kendi API'sinin neden yeterli olmadığını yazın.
3. Dependency'nin bakım ve lisans durumunu kontrol edin.
4. Küçük bir kullanım alanında deneyin.
5. `DECISIONS.md` içine eklemeyi değerlendirin.
