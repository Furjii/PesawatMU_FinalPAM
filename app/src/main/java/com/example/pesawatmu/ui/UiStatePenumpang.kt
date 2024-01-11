import com.example.contactapp_with_firebase.ui.AddUIState
import com.example.pesawatmu.model.Penumpang

data class AddUIState2(
    val addBoarding: AddBoarding = AddBoarding(),
)

data class AddBoarding(
    val nik : String = "",
    val nama: String = "",
    val jenis_kelamin: String = "",
    val umur: String = "",
)

fun AddBoarding.toPenumpang() = Penumpang(
    nik = nik,
    nama_penumpang = nama,
    jenis_kelamin = jenis_kelamin,
    umur_penumpang = umur
)

data class DetailUIState2(
    val addBoarding: AddBoarding = AddBoarding(),
)

fun Penumpang.toDetailPenumpang(): AddBoarding = AddBoarding(
        nik = nik,
        nama = nama_penumpang,
        jenis_kelamin = jenis_kelamin,
        umur = umur_penumpang
    )

fun Penumpang.toUIStatePenumpang(): AddUIState2 = AddUIState2(
    addBoarding = this.toDetailPenumpang(),
)

data class HomeUIState2(
    val listPenumpang: List<Penumpang> = listOf(),
    val dataLength: Int = 0
)