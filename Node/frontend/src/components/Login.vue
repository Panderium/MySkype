<template>
    <div class="login">
        <div class="col s12 m12 l12">
            <div class="card-panel">
                <div class="row">
                    <form class="col s12">
                        <div class="row">
                            <form>
                                <div class="input-field col s4">
                                    <i class="mdi-action-account-circle prefix"></i>
                                    <input v-model="username" id="icon_prefix" type="text"
                                           class="validate" required>
                                    <label for="icon_prefix">First Name</label>
                                </div>
                                <div class="input-field col s4">
                                    <i class="mdi-action-lock-outline prefix"></i>
                                    <input v-model="password" id="icon_password" type="password"
                                           class="validate" required>
                                    <label for="icon_password">Password</label>
                                </div>
                                <div class="input-field col s4">
                                    <div class="input-field col s12">
                                        <button v-on:keyup.enter="submit" @click="submit"
                                                class="btn waves-effect waves-light">
                                            <i class="mdi-action-lock-open"></i> Login
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Login",
        data() {
            return {
                response: null,
                errors: null,
                username: null,
                password: null
            }
        },
        methods: {
            askLogged() {
                axios.get("api/login")
                    .then(response => {
                    this.response = response.data;
                    this.$emit("logged", this.response);
                    console.log(this.response)
                    })
                    .catch(error => {
                        this.errors = error
                        console.log(this.errors)
                    });
            },
            submit() {
                console.log("sldkfn");
                let content = {
                    "name": this.username,
                    "hash": this.password
                };
                axios.post("api/login", content)
                    .catch(error => {
                        this.errors = error
                        console.log(this.errors)
                    });
                this.askLogged();
                console.log("fin")
            }
        },
        mounted() {
            this.askLogged();
        }
    }
</script>

<style scoped>

</style>