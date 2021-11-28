package pl.pracownia.pokertables.model

import com.google.gson.annotations.SerializedName

data class Ring (
  val id: Long,
  val name: String,
  val url: String,

  @SerializedName("server_version")
  val serverVersion: Long,

  val type: Type,
  val blinds: Blinds,
  val brings: Brings,
  val closed: Boolean,
  val fast: Boolean,
  val seats: List<Seat>,
  val description: String,
  val promotions: List<Promotion>,

  @SerializedName("template_active")
  val templateActive: Boolean,

  @SerializedName("min_endorsement_rank")
  val minEndorsementRank: Long,

  val avgPot: Long,
  val avgStack: Long,
  val handHours: Long,

  @SerializedName("playersIds")
  val playersIDS: List<Long>,

  val waitingList: List<WaitingList>,
  val stake: Stake,

  @SerializedName("quickUrl")
  val quickURL: String,

  val template: Long,
  val game: Game,
  val antes: Long? = null
)

data class Blinds (
  val small: Long,
  val big: Long
)

data class Brings (
  val min: Long,
  val max: Long
)

data class Game (
  val name: String,
  val variation: Variation,
  val limit: Limit
)

enum class Limit(val value: String) {
  FixedLimit("fixed limit"),
  MixedLimit("mixed limit"),
  NoLimit("no limit"),
  PotLimit("pot limit");

  companion object {
    public fun fromValue(value: String): Limit = when (value) {
      "fixed limit" -> FixedLimit
      "mixed limit" -> MixedLimit
      "no limit"    -> NoLimit
      "pot limit"   -> PotLimit
      else          -> throw IllegalArgumentException()
    }
  }
}

enum class Variation(val value: String) {
  Holdem("holdem"),
  Omaha("omaha"),
  OmahaHiLo("omaha_hi_lo"),
  Royal("royal"),
  Stud7("stud7"),
  Stud7HiLo("stud7HiLo");

  companion object {
    public fun fromValue(value: String): Variation = when (value) {
      "holdem"      -> Holdem
      "omaha"       -> Omaha
      "omaha_hi_lo" -> OmahaHiLo
      "royal"       -> Royal
      "stud7"       -> Stud7
      "stud7HiLo"   -> Stud7HiLo
      else          -> throw IllegalArgumentException()
    }
  }
}

data class Promotion (
  val name: String,
  val url: String
)


data class Seat (
  val id: Long? = null,
  val username: String? = null,
  val chips: Long? = null,
  val url: String? = null,
  val avatar: String? = null,
  val country: String? = null
)

enum class Stake(val value: String) {
  Elite("elite"),
  High("high"),
  Low("low"),
  Medium("medium");

  companion object {
    public fun fromValue(value: String): Stake = when (value) {
      "elite"  -> Elite
      "high"   -> High
      "low"    -> Low
      "medium" -> Medium
      else     -> throw IllegalArgumentException()
    }
  }
}

enum class Type(val value: String) {
  Ring("ring");

  companion object {
    public fun fromValue(value: String): Type = when (value) {
      "ring" -> Ring
      else   -> throw IllegalArgumentException()
    }
  }
}

data class WaitingList (
  val user: User,
  val enter: String
)

data class User (
  val id: Long,
  val url: String,
  val avatar: String,
  val rank: Long,
  val status: Status,
  val premium: Boolean,
  val name: String? = null,
  val purchases: Boolean,
  val username: String,
  val chips: Long
)

enum class Status(val value: String) {
  Online("online"),
  Offline("offline");

  companion object {
    public fun fromValue(value: String): Status = when (value) {
      "online" -> Online
      "offline" -> Offline
      else     -> throw IllegalArgumentException()
    }
  }
}