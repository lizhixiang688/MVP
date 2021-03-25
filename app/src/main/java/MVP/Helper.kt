package MVP

class Helper(val iView: IView):Callback {
    override fun onResult(text: String) {
     iView.updateUi(text)
    }
}