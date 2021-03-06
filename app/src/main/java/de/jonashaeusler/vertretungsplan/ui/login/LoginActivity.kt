package de.jonashaeusler.vertretungsplan.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.jonashaeusler.vertretungsplan.R
import de.jonashaeusler.vertretungsplan.data.local.isLoggedIn
import de.jonashaeusler.vertretungsplan.data.local.setApiPassword
import de.jonashaeusler.vertretungsplan.data.local.setApiUser
import de.jonashaeusler.vertretungsplan.data.local.setClassShortcut
import de.jonashaeusler.vertretungsplan.data.local.setPassword
import de.jonashaeusler.vertretungsplan.data.local.setUsername
import de.jonashaeusler.vertretungsplan.data.network.dsb.LoginTask
import de.jonashaeusler.vertretungsplan.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Activity to get the user logged in.
 *
 * @see LoginTask
 */
class LoginActivity : AppCompatActivity(), LoginTask.OnLogin {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        setTitle(R.string.login)

        login.setOnClickListener {
            when {
                username.text.isBlank() -> username.error = getString(R.string.error_blank_field)
                password.text.isBlank() -> password.error = getString(R.string.error_blank_field)
                else -> {
                    LoginTask(this).execute(username.text.toString(), password.text.toString())
                    login.isEnabled = false
                }
            }
        }

        expandApiLogin.setOnClickListener {
            apiLoginContainer.visibility =
                    if (apiLoginContainer.visibility == View.VISIBLE) View.GONE
                    else View.VISIBLE
        }
    }

    override fun onLoginSucceeded() {
        setUsername(username.text.toString())
        setPassword(password.text.toString())
        setClassShortcut(text.text.toString())
        setApiUser(apiUsername.text.toString())
        setApiPassword(apiPassword.text.toString())
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onLoginFailed() {
        Toast.makeText(this, getString(R.string.error_login_failed), Toast.LENGTH_SHORT).show()
        login.isEnabled = true
    }
}
