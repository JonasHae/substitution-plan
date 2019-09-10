package de.jonashaeusler.vertretungsplan.data.entities

/**
 * Basic model for a so called "timetable" retrieved from dsb.
 */
data class Timetable(
        val isHtml: Boolean,
        val timetableDate: String,
        val timetableGroupName: String,
        val timetableTitle: String,
        val timetableUrl: String
)
