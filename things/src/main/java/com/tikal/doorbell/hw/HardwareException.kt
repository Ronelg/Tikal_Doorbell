package com.tikal.doorbell.hw

/**
 * Runtime hardware exception.
 */
class HardwareException(component: String, reason: String?) :
        RuntimeException("error in component $component: $reason")