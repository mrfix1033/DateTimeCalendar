package ru.mrfix1033.datetimecalendar

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class Person(val name: String, val surname: String, val phone: String, val birthMillis: Long, val image: Uri?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        Uri.parse(parcel.readString())
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(name)
        p0.writeString(surname)
        p0.writeString(phone)
        p0.writeLong(birthMillis)
        p0.writeString(image.toString())
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}