<template>
    <div class="ConversationList">

        <nav class="panel">
            <p class="panel-heading">
                Conversations
            </p>
            <div class="panel-block">
                <p class="control has-icons-left">
                    <input class="input is-small" type="text" placeholder="search">
                    <span class="icon is-small is-left">
                            <i class="fas fa-search" aria-hidden="true"></i>
                        </span>
                </p>
            </div>
            <div v-for="conv in response">
                <a class="panel-block" @click="$emit('switch-conversation', conv.name)">
                    <div class="media-left">
                        <figure class="image is-64x64">
                            <img src="https://bulma.io/images/placeholders/128x128.png" alt="Image">
                        </figure>
                    </div>
                    <div class="media-content">
                        <div class="content">
                            <p>
                                <strong>{{ conv.name }}</strong>
                                <br>
                            <div v-if="typeof conv.messages[0] !== 'undefined'">
                                {{ conv.messages[0].body }}
                            </div>
                            </p>
                        </div>
                    </div>
                </a>
            </div>
        </nav>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "ConversationList",
        data() {
            return {
                response: [],
                errors: []
            }
        },
        mounted() {
            axios.get(`api/conversation`)
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