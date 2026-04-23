package com.soficom.fintech

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform