package com.example.girls4girls.presentation.videoblogsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.R
import com.example.girls4girls.data.Mentor
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.data.repository.VideoBlogsRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import kotlinx.coroutines.launch

class VideoblogsListViewModel(private val repository: VideoBlogsRepository) : ViewModel() {

    val _videosList = MutableLiveData<List<VideoBlog>>()
    var videosList = MutableLiveData<List<VideoBlog>>()

    fun getVideos(){
        viewModelScope.launch {
            val response = repository.getVideoBlogs()
            Log.d(TAG, "getVideos: ${response.body()}")
            if (response.isSuccessful){
                _videosList.postValue(response.body()!!.videosList)
            } else {
                Log.d(TAG, "getVideos: ${response.errorBody().toString()}")
            }

        }
    }


//    val mentor1 = Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova)
//    val mentor2 = Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva)
//    val mentor3 = Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva)
//    val mentor4 = Mentor("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova)
//
//    val video1 = VideoBlog(0, NAME1, "21/12/22", "Бизнес", mentor1, TEXT1,192,true, false, LINK)
//    val video2 = VideoBlog(1, NAME2, "01/10/22", "Будущее", mentor2, TEXT2,12,false, true, LINK2)
//    val video3 = VideoBlog(2, NAME3, "01/07/22", "Еда", mentor3, TEXT3,999,false, true, LINK3)
//    val video4 = VideoBlog(3, NAME4, "08/06/22", "Образование",mentor2, TEXT4,10000, true, false, LINK2)
//    val video5 = VideoBlog(4, NAME5, "02/12/21", "Программирование", mentor4, TEXT2,0,false, true, LINK)
//
//    val videos = listOf<VideoBlog>(
//        video1,
//        video2,
//        video3,
//        video4,
//        video5
//    )

    companion object{
        val NAME1 = "Менторская программа и тренинги | Проект «Girls for Girls» для девочек | Вопрос-ответ часть 1"
        val NAME2 = "Трудности в работе | Проект «Girls for Girls» | Вопрос-ответ часть 2"
        val NAME3 = "Первое впечатление друг о друге | Подробнее о нас | Душевная беседа | Girls for Girls"
        val NAME4 = "Что даёт и забирает энергию"
        val NAME5 = "Как подготовиться к переезду в Канаду?"
        val TEXT1 = "В этом видео вы узнаете от сооснователей программы «Girls for Girls» об истории создания проекта, менторской программе, тренингов и о разных нюансах в работе. Также о том, как проект помогает развить лидерские качества у девочек со всех регионов Кыргызстана. "
        val TEXT2 = "В новом видео соосновательницы проекта «Girls for Girls» Нурайым и Айсезим подробно расскажут о своей работе, ответят на все ваши вопросы и поделятся трудностями, с которыми они сталкиваются в процессе реализации проекта. Они также расскажут о своих ролях и актуальности проекта в регионах."
        val TEXT3 = "Добро пожаловать на наш канал! Сегодня в этом выпуске мы расскажем подробнее о нас, как мы познакомились и вспомним наши первые впечатления друг о друге."
        val TEXT4 = "В этом видео расскажем, что даёт энергию и что ее отбирает. Основы основ!\n" +
                "\n" +
                "Если вы не чувствуете себя бодрым и энергичным с самого утра и до позднего вечера, если вы сложно просыпаетесь по утрам, если вам не хватает сил и энергии реализовывать все задуманное, смотрите наше видео, и трансформируйте свою жизнь!"
        val LINK = "https://www.youtube.com/watch?v=l-ooCKssPv4"
        val LINK2 = "https://www.youtube.com/watch?v=zvTr3P43yUg"
        val LINK3 = "https://www.youtube.com/watch?v=_jwBhhbf-PQ"
    }
}