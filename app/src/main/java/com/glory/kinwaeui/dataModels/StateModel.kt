package com.glory.kinwaeui.dataModels

data class StateModel(val name : String, val locals : List<String>){

    override fun toString(): String {
        return "StateModel(name='$name', locals='$locals')"
    }
}