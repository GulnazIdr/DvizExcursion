#-dontwarn io.opentelemetry.api.trace.Span
#-dontwarn io.opentelemetry.api.trace.SpanContext
#-dontwarn io.opentelemetry.api.trace.TraceFlags
# Keep your app classes
-keep class org.gulnazidr.stepik.** { *; }

# Keep Koin
-keep class org.koin.** { *; }

# Keep Firebase
-keep class com.google.firebase.** { *; }