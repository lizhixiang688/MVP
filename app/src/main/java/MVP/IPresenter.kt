package MVP

class IPresenter (val iView: IView){
    val helper =Helper(iView)
    val model = IModel(helper)


    fun request(url:String){
        model.request(url)
    }
}