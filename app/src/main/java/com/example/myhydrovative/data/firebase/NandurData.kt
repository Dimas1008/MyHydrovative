package com.example.myhydrovative.data.firebase

class NandurData {
    /**set Data*/
    var name :String? = null
    var description:String? = null
    var image:String? = null
    constructor(){}

    constructor(
        name:String?,
        description:String?,
        image:String?
    ){
        this.name = name
        this.description = description
        this.image = image
    }
}