<template>
    <div class="conversation">
        <div class="card large" style="overflow: auto">

            <h4 class="header center">{{ this.username }}</h4>
            <div class="card-content">
                <div v-if="response !== null">
                    <div v-for="message in this.response.messages">
                        <div class="materialize-red-text" v-if="message.sender">
                            {{ message.body }}
                        </div>
                        <div v-else>
                            {{ message.body }}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="input-field">
            <i class="material-icons prefix">mode_edit</i>
            <input v-on:keyup.enter="submit" v-model="message" id="first_name2" type="text" class="validate">
            <label class="active" for="first_name2">Message</label>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Conversation",
        props: {
            username: String
        },
        data() {
            return {
                response: null,
                errors: null,
                message: null
            }
        },
        watch: {
            username(newVal, oldVal) {
                axios.get("api/conversation/" + this.username + "/")
                    console.log("Old val ", oldVal, "new val ", newVal)
                    .then(response => {
                        this.response = response.data
                    })
                    .catch(error => {
                        this.errors.push(error)
                    })

            }

        },
        methods: {
            submit() {
                let content = {
                    "body": this.message,
                    "sender": true
                };
                this.message = null;
                axios.put("api/conversation/" + this.username + "/", content)
                    .then(response => {
                        this.response = response.data
                    })
                    .catch(error => {
                        this.errors = error
                    });
            }
        },
        updated() {
            axios.get("api/conversation/" + this.username + "/")
                .then(response => {
                    this.response = response.data
                })
                .catch(error => {
                    this.errors.push(error)
                })
        }
    }
</script>

<style scoped>

</style>